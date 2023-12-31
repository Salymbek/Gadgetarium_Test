package peaksoft.house.gadgetariumb9.services.serviceImpl;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import peaksoft.house.gadgetariumb9.config.security.JwtService;
import peaksoft.house.gadgetariumb9.dto.request.order.OrderUserRequest;
import peaksoft.house.gadgetariumb9.dto.response.order.*;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.enums.Status;
import peaksoft.house.gadgetariumb9.enums.TypeDelivery;
import peaksoft.house.gadgetariumb9.exceptions.BadCredentialException;
import peaksoft.house.gadgetariumb9.exceptions.NotFoundException;
import peaksoft.house.gadgetariumb9.models.Basket;
import peaksoft.house.gadgetariumb9.models.Order;
import peaksoft.house.gadgetariumb9.models.SubProduct;
import peaksoft.house.gadgetariumb9.models.User;
import peaksoft.house.gadgetariumb9.repositories.OrderRepository;
import peaksoft.house.gadgetariumb9.repositories.UserRepository;
import peaksoft.house.gadgetariumb9.services.OrderService;
import peaksoft.house.gadgetariumb9.template.OrderTemplate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  public static final String UTF_8 = "UTF-8";

  private final OrderTemplate orderTemplate;

  private final OrderRepository orderRepository;

  private final JavaMailSender emailSender;

  private final TemplateEngine templateEngine;

  private final JwtService jwtService;

  private final UserRepository userRepository;

  @Override
  public OrderPaginationAdmin getAllOrderAdmin(String status, int pageSize, int pageNumber, LocalDate startDate, LocalDate endDate) {
    return orderTemplate.getAllOrderAdmin(status, startDate, endDate, pageSize, pageNumber);
  }

  @Override
  public SimpleResponse updateStatus(Long orderId, String status) {
    Order order = orderRepository.findById(orderId).orElseThrow(() -> {
      log.error(String.format("Order with id - %s is not found!", orderId));
      return new NotFoundException(String.format("Order with id - %s not found!", orderId));
    });
    Status newStatus;
    switch (status) {
      case "В ожидании" -> newStatus = Status.PENDING;
      case "Готов к выдаче" -> newStatus = Status.READY_FOR_DELIVERY;
      case "Получен" -> newStatus = Status.RECEIVED;
      case "Отменить" -> newStatus = Status.CANCEL;
      case "Курьер в пути" -> newStatus = Status.COURIER_ON_THE_WAY;
      case "Доставлен" -> newStatus = Status.DELIVERED;
      default -> {
        log.error("Статус не соответствует!");
        return SimpleResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Статус не соответствует!")
            .build();
      }
    }
    order.setStatus(newStatus);

    orderRepository.save(order);
    log.error("successfully updated");
    return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .message("successfully updated")
        .build();
  }

  @Override
  public OrderInfoResponse getOrderInfo(Long orderId) {
    return orderTemplate.getOrderInfo(orderId);
  }

  @Override
  public SimpleResponse multiDeleteOrder(List<Long> orders) {
    for (Long orderId : orders) {
      Order order = orderRepository.findById(orderId).orElseThrow(() -> {
        log.error("Order with %s is not found" + orderId);
        return new NotFoundException("Order with %s is not found" + orderId);
      });
      order.getSubProducts().forEach(x -> x.getOrders().remove(order));

      orderRepository.delete(order);
    }

    return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .message("SubProducts with given IDs are deleted")
        .build();
  }

  @Override
  public SimpleResponse singleDelete(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(() -> {
      log.error("Order with %s is not found" + orderId);
      return new NotFoundException("Order with %s is not found" + orderId);
    });
    order.getSubProducts().forEach(x -> x.getOrders().remove(order));

    orderRepository.delete(order);

    return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .message("SubProducts with given IDs are deleted")
        .build();
  }

  @Override
  public OrderUserResponse saveOrder(OrderUserRequest request) {

    User user = jwtService.getAuthenticationUser();
    List<SubProduct> selectedProducts = getProductsFromCartByEmailAndIds(user.getEmail(), request.getSubProductIds());

    int totalQuantity = selectedProducts.size();
    BigDecimal totalPrice = BigDecimal.ZERO;
    int totalDiscount = 0;

    for (SubProduct subProduct : selectedProducts) {
      BigDecimal productCost = subProduct.getPrice();
      totalPrice = totalPrice.add(productCost);

      if (subProduct.getDiscount() != null) {
        int discountPercentage = subProduct.getDiscount().getSale();
        totalDiscount += discountPercentage;
      }

      int currentQuantity = subProduct.getQuantity();
      if (currentQuantity > 0) {
        subProduct.setQuantity(currentQuantity - 1);
      }

      if (subProduct.getQuantity() == 0) {
        Basket basket = getBasketByEmailAndProductId(user.getEmail(), subProduct.getId());
        if (basket != null) {
          basket.getSubProducts().remove(subProduct);
        }
      }
    }


    ZonedDateTime data = ZonedDateTime.now();
    LocalDate localDate = data.toLocalDate();

    Order order = new Order();
    order.setSubProducts(selectedProducts);
    order.setDateOfOrder(data);
    order.setTotalPrice(totalPrice);
    order.setQuantity(totalQuantity);
    order.setTotalDiscount(totalDiscount);
    order.setTypeDelivery(request.getTypeDelivery());
    order.setTypePayment(request.getTypePayment());
    int orderNumber = generate();
    order.setOrderNumber(orderNumber);

    Status status = Status.IN_PROCESSING;
    order.setStatus(status);
    TypeDelivery typeDelivery = order.getTypeDelivery();

    try {
      Context context = new Context();
      context.setVariable("orderNumber", order.getOrderNumber());
      context.setVariable("dateOfOrder", localDate);
      context.setVariable("statusOrder", status.getValue());
      context.setVariable("user", request.getFirstName() + " " + request.getLastName());
      context.setVariable("phoneNumber", request.getPhoneNumber());
      context.setVariable("deliveryType", typeDelivery.getValue());
      String text = templateEngine.process("templates/order-email-template.html", context);
      MimeMessage message = getMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
      helper.setPriority(1);
      helper.setSubject("Gadgetarium");
      helper.setFrom("shop.gadgetarium.kg@gmail.com");
      helper.setTo(request.getEmail());
      helper.setText(text, true);
      orderRepository.save(order);
      order.setUser(user);
      emailSender.send(message);
      return OrderUserResponse.builder()
          .status(HttpStatus.OK)
          .orderNumber(order.getOrderNumber())
          .message(String.format("""
                                  Ваша заявка №%s от %s оформлена.\s
                            Вся актуальная информация о статусе исполнения\s
                                 заказа придет на указанный email:\s
                                                %s
                            """, order.getOrderNumber(), localDate, request.getEmail()))
          .build();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      throw new BadCredentialException("Message not sent!");
    }
  }

  public Basket getBasketByEmailAndProductId(String userEmail, Long productId) {
    User user = userRepository.findByEmail(userEmail);

    if (user != null) {
      List<Basket> baskets = user.getBaskets();

      for (Basket basket : baskets) {
        List<SubProduct> productsInBasket = basket.getSubProducts();

        for (SubProduct subProduct : productsInBasket) {
          if (subProduct.getId().equals(productId)) {
            return basket;
          }
        }
      }
    }

    return null;
  }


  public List<SubProduct> getProductsFromCartByEmailAndIds(String userEmail, List<Long> productIds) {
    User user = userRepository.findByEmail(userEmail);
    List<Basket> baskets = user.getBaskets();
    List<SubProduct> selectedProducts = new ArrayList<>();

    for (Basket basket : baskets) {
      List<SubProduct> productsInBasket = basket.getSubProducts();

      for (SubProduct subProduct : productsInBasket) {
        if (productIds.contains(subProduct.getId())) {
          selectedProducts.add(subProduct);
        }
      }
    }

    return selectedProducts;
  }

  public int generate() {
    return (int) (System.currentTimeMillis() % 1000000);
  }

  private MimeMessage getMimeMessage() {
    return emailSender.createMimeMessage();
  }

  public List<OrderHistoryResponse> getOrdersByUserId(Long userId) {
    return orderTemplate.getOrdersByUserId(userId);
  }

  @Override
  public OrderInfoByUserResponse getOrderByUser(Long orderId, Long userId) {
    orderRepository.findById(orderId).orElseThrow(() -> {
      log.error("Order with %s is not found" + orderId);
      return new NotFoundException("Order with %s is not found" + orderId);
    });
    return orderTemplate.getOrderByUser(orderId, userId);
  }

  @Override
  public OrderSearchPagination getOrderSearch(String keyword, String sortType,
      LocalDate startDate, LocalDate endDate, int pageSize, int pageNumber) {
    return orderTemplate.orderSearch(keyword,sortType, startDate, endDate, pageSize, pageNumber);
  }
}