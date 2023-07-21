package peaksoft.house.gadgetariumb9.service.impl;

import jakarta.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.entities.Brand;
import peaksoft.house.gadgetariumb9.entities.Category;
import peaksoft.house.gadgetariumb9.entities.Laptop;
import peaksoft.house.gadgetariumb9.entities.Phone;
import peaksoft.house.gadgetariumb9.entities.Product;
import peaksoft.house.gadgetariumb9.entities.SmartWatch;
import peaksoft.house.gadgetariumb9.entities.SubProduct;
import peaksoft.house.gadgetariumb9.entities.Subcategory;
import peaksoft.house.gadgetariumb9.exception.NotFoundException;
import peaksoft.house.gadgetariumb9.repository.BrandRepository;
import peaksoft.house.gadgetariumb9.repository.CategoryRepository;
import peaksoft.house.gadgetariumb9.repository.ProductRepository;
import peaksoft.house.gadgetariumb9.repository.SubProductRepository;
import peaksoft.house.gadgetariumb9.repository.SubcategoryRepository;
import peaksoft.house.gadgetariumb9.service.SubProductService;

@Service
@AllArgsConstructor
@Slf4j
public class SubProductServiceImpl implements SubProductService {

//  private final ProductRepository productRepository;
//
//  private final BrandRepository brandRepository;
//
//  private final SubcategoryRepository subCategoryRepository;
//
//  private final CategoryRepository categoryRepository;
//  private final SubProductRepository subProductRepository;
//
//  @Override
//  public SimpleResponse saveSubProduct(ProductRequest productRequest) {
//
//    Subcategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
//        .orElseThrow(() -> new NotFoundException(
//            "SubCategory with id: " + productRequest.getSubCategoryId() + "is not found"));
//
//    Brand brand = brandRepository.findById(productRequest.getBrandId())
//        .orElseThrow(() -> new NotFoundException(
//            "Brand with id: " + productRequest.getBrandId() + "is not found"));
//
//    Category category = categoryRepository.findById(productRequest.getCategoryId())
//        .orElseThrow(() -> new NotFoundException(
//            "Category with id: " + productRequest.getCategoryId() + "is not found"));
//    Product product = Product.builder()
//        .subCategory(subCategory)
//        .brand(brand)
//        .category(category)
//        .name(productRequest.getName())
//        .dataOfIssue(ZonedDateTime.now())
//        .createdAt(ZonedDateTime.now())
//        .guarantee(productRequest.getGuarantee())
//        .build();
//
//    List<SubProduct> subProducts = productRequest.getSubProductRequests().stream()
//        .map(subProductRequest -> {
//          SubProduct subProduct = new SubProduct();
//          subProduct.setCodeColor(subProductRequest.getCodeColor());
//          subProduct.setRom(subProductRequest.getRom());
//          subProduct.setRam(subProductRequest.getRam());
//          subProduct.setScreenResolution(subProductRequest.getScreenResolution());
//          subProduct.setAdditionalFeatures(subProductRequest.getAdditionalFeatures());
//          subProduct.setQuantity(subProductRequest.getQuantity());
//          subProduct.setImages(subProductRequest.getImages());
//          subProduct.setPrice(subProductRequest.getPrice());
//          int articleCode = generateArticleCode();
//          subProduct.setArticleNumber(articleCode);
//
//          subProductRepository.save(subProduct);
//
//          switch (category.getTitle()) {
//            case "Laptops" -> {
//              Laptop laptop = new Laptop();
//              laptop.setProcessor(subProductRequest.getProcessor());
//              laptop.setPurpose(subProductRequest.getPurpose());
//              laptop.setVideoMemory(subProductRequest.getVideoMemory());
//              laptop.setScreenSize(subProductRequest.getScreenSize());
//              subProduct.setLaptop(laptop);
//            }
//            case "Phones and tablets" -> {
//              Phone phone = new Phone();
//              phone.setSim(subProductRequest.getSim());
//              phone.setDiagonalScreen(subProductRequest.getDiagonalScreen());
//              phone.setBatteryCapacity(subProductRequest.getBatteryCapacity());
//              subProduct.setPhone(phone);
//            }
//            case "SmartWatches фnd bracelets" -> {
//              SmartWatch smartWatch = new SmartWatch();
//              smartWatch.setAnInterface(subProductRequest.getAnInterface());
//              smartWatch.setHUllShape(subProductRequest.getHullShape());
//              smartWatch.setMaterialBracelet(subProductRequest.getMaterialBracelet());
//              smartWatch.setHousingMaterial(subProductRequest.getHousingMaterial());
//              smartWatch.setGender(subProductRequest.getGender());
//              smartWatch.setWaterproof(subProductRequest.isWaterproof());
//              smartWatch.setDisplayDiscount(subProductRequest.getDisplayDiscount());
//              subProduct.setSmartWatch(smartWatch);
//            }
//          }
//          return subProduct;
//        })
//        .toList();
//
//    product.setSubProducts(subProducts);
//
//    productRepository.save(product);
//
//    return SimpleResponse.builder()
//        .status(HttpStatus.OK)
//        .message("successfully added")
//        .build();
//  }
//
//  private int generateArticleCode() {
//    Random random = new Random();
//    return random.nextInt(9000) + 1000;
//  }


  private final JdbcTemplate jdbcTemplate;



  public InfographicsResponse infographics(String period) {
//    LocalDate currentDate = LocalDate.now();
//    Tuple resultByDay = subProductRepository.getRansomsSummaryByDay(currentDate);
//    Tuple resultByMonth = subProductRepository.getRansomsSummaryByMonth(currentDate.getYear(), currentDate.getMonthValue());
//    Tuple resultByYear = subProductRepository.getRansomsSummaryByYear(currentDate.getYear());
//
//    Long totalQuantityByDay = resultByDay.get("totalQuantity", Long.class);
//    BigDecimal totalTotalPriceByDay = resultByDay.get("totalTotalPrice", BigDecimal.class);
//
//    Long totalQuantityByMonth = resultByMonth.get("totalQuantity", Long.class);
//    BigDecimal totalTotalPriceByMonth = resultByMonth.get("totalTotalPrice", BigDecimal.class);
//
//    Long totalQuantityByYear = resultByYear.get("totalQuantity", Long.class);
//    BigDecimal totalTotalPriceByYear = resultByYear.get("totalTotalPrice", BigDecimal.class);

//    log.info("Получаем инфографику!");
//    String sql = """
//                SELECT COALESCE(SUM(CASE WHEN o.status = 'DELIVERED' THEN totalPrice END), 0) AS ransom_price,
//                       COALESCE(COUNT(CASE WHEN o.status = 'DELIVERED' THEN totalPrice END), 0) AS count_of_ransom,
//                       COALESCE( SUM(CASE WHEN o.status = 'EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY' THEN totalPrice END), 0) AS order_price,
//                       COALESCE( COUNT(CASE WHEN o.status = 'EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY' THEN totalPrice END), 0) AS count_of_orders,
//                       COALESCE(SUM(CASE
//                               WHEN o.dateOfOrder = current_date and 'day' = ? THEN totalPrice
//                               WHEN extract(year from o.dateOfOrder) = extract(year from current_date) and
//                                 extract(month from o.dateOfOrder) = extract(month from current_date) and 'month' = ? THEN totalPrice
//                               WHEN extract(year from o.dateOfOrder) = extract(year from current_date) and 'year' = ?
//                                   THEN totalPrice END),0)
//                       AS current_period,
//                       COALESCE(SUM(CASE
//                               WHEN o.dateOfOrder = (current_date - interval '1 day') and 'day' = ? THEN totalPrice
//                               WHEN extract(year from o.dateOfOrder) = extract(year from current_date) and
//                                 extract(month from o.dateOfOrder) = extract(month from current_date - interval '1 month') and
//                                    'month' = ? THEN totalPrice
//                               WHEN extract(year from o.dateOfOrder) = extract(year from current_date - interval '1 year') and 'year' = ?
//                                   THEN totalPrice END),0)
//                       AS previous_period
//                FROM orders o;
//                """;
//    return jdbcTemplate.query(sql, (resulSet, i) -> new InfographicsResponse(
//            resulSet.getBigDecimal("ransom_price"),
//            resulSet.getInt("count_of_ransoms"),
//            resulSet.getBigDecimal("order_price"),
//            resulSet.getInt("count_of_orders"),
//            resulSet.getBigDecimal("current_period"),
//            resulSet.getBigDecimal("previous_period")
//        ),
//        period,
//        period,
//        period,
//        period,
//        period,
//        period
//    ).stream().findFirst().orElseThrow(() -> {
//      log.error("Этот ответ не найден!!");
//      throw new NotFoundException("Этот ответ не найден!!");
//    });

    //--------------------------------------------2
//    LocalDate currentDate = LocalDate.now();
//    LocalDate prevDate = currentDate.minusDays(1);
//    LocalDate currentMonthStart = currentDate.withDayOfMonth(1);
//    LocalDate prevMonthStart = currentMonthStart.minusMonths(1);
//    LocalDate currentYearStart = currentDate.withDayOfYear(1);
//    LocalDate prevYearStart = currentYearStart.minusYears(1);
//
//    String deliveredQuery = "SELECT SUM(quantity) AS deliveredQuantity, SUM(totalPrice) AS deliveredTotalPrice FROM Order WHERE status = 'DELIVERED';";
//    String waitingQuery = "SELECT SUM(quantity) AS waitingQuantity, SUM(totalPrice) AS waitingTotalPrice FROM Order WHERE status = 'WAITING';";
//
//    String currentDayQuery = "SELECT SUM(totalPrice) AS currentDayTotalPrice FROM Order WHERE dateOfOrder = ?;";
//    String currentMonthQuery = "SELECT SUM(totalPrice) AS currentMonthTotalPrice FROM Order WHERE dateOfOrder >= ?;";
//    String currentYearQuery = "SELECT SUM(totalPrice) AS currentYearTotalPrice FROM Order WHERE dateOfOrder >= ?;";
//
//    String prevDayQuery = "SELECT SUM(totalPrice) AS prevDayTotalPrice FROM Order WHERE dateOfOrder = ?;";
//    String prevMonthQuery = "SELECT SUM(totalPrice) AS prevMonthTotalPrice FROM Order WHERE dateOfOrder >= ? AND dateOfOrder < ?;";
//    String prevYearQuery = "SELECT SUM(totalPrice) AS prevYearTotalPrice FROM Order WHERE dateOfOrder >= ? AND dateOfOrder < ?;";
//
//    InfographicsResponse response = new InfographicsResponse();
//
//    // DELIVERED
//    jdbcTemplate.query(deliveredQuery, new RowMapper<Void>() {
//      @Override
//      public Void mapRow(ResultSet resultSet, int i) throws SQLException {
//        response.setDeliveredQuantity(resultSet.getInt("deliveredQuantity"));
//        response.setDeliveredTotalPrice(resultSet.getBigDecimal("deliveredTotalPrice"));
//        return null;
//      }
//    });
//
//    // WAITING
//    jdbcTemplate.query(waitingQuery, new RowMapper<Void>() {
//      @Override
//      public Void mapRow(ResultSet resultSet, int i) throws SQLException {
//        response.setWaitingQuantity(resultSet.getInt("waitingQuantity"));
//        response.setWaitingTotalPrice(resultSet.getBigDecimal("waitingTotalPrice"));
//        return null;
//      }
//    });
//
//    // CURRENT
//    response.setCurrentDayTotalPrice(jdbcTemplate.queryForObject(currentDayQuery, BigDecimal.class, currentDate));
//    response.setCurrentMonthTotalPrice(jdbcTemplate.queryForObject(currentMonthQuery, BigDecimal.class, currentMonthStart));
//    response.setCurrentYearTotalPrice(jdbcTemplate.queryForObject(currentYearQuery, BigDecimal.class, currentYearStart));
//
//    // PREVIOUS
//    response.setPrevDayTotalPrice(jdbcTemplate.queryForObject(prevDayQuery, BigDecimal.class, prevDate));
//    response.setPrevMonthTotalPrice(jdbcTemplate.queryForObject(prevMonthQuery, BigDecimal.class, prevMonthStart, currentMonthStart));
//    response.setPrevYearTotalPrice(jdbcTemplate.queryForObject(prevYearQuery, BigDecimal.class, prevYearStart, currentYearStart));
//
//    return response;
//  }
    //-------------------------------------------3
//    LocalDate startDate = null;
//    LocalDate endDate = LocalDate.now();
//
//    if (period.contains("day")) {
//      startDate = endDate.minusDays(1);
//    } else if (period.contains("month")) {
//      startDate = endDate.withDayOfMonth(1);
//    } else if (period.contains("year")) {
//      startDate = endDate.withDayOfYear(1);
//    }
//
//    String deliveredQuery = "SELECT SUM(quantity) AS deliveredQuantity, SUM(totalPrice) AS deliveredTotalPrice FROM Order WHERE status = 'DELIVERED' AND dateOfOrder >= ? AND dateOfOrder <= ?;";
//    String waitingQuery = "SELECT SUM(quantity) AS waitingQuantity, SUM(totalPrice) AS waitingTotalPrice FROM Order WHERE status = 'WAITING' AND dateOfOrder >= ? AND dateOfOrder <= ?;";
//
//    String currentDayQuery = "SELECT SUM(totalPrice) AS currentDayTotalPrice FROM Order WHERE dateOfOrder = ?;";
//    String currentMonthQuery = "SELECT SUM(totalPrice) AS currentMonthTotalPrice FROM Order WHERE dateOfOrder >= ?;";
//    String currentYearQuery = "SELECT SUM(totalPrice) AS currentYearTotalPrice FROM Order WHERE dateOfOrder >= ?;";
//
//    String prevDayQuery = "SELECT SUM(totalPrice) AS prevDayTotalPrice FROM Order WHERE dateOfOrder = ?;";
//    String prevMonthQuery = "SELECT SUM(totalPrice) AS prevMonthTotalPrice FROM Order WHERE dateOfOrder >= ? AND dateOfOrder < ?;";
//    String prevYearQuery = "SELECT SUM(totalPrice) AS prevYearTotalPrice FROM Order WHERE dateOfOrder >= ? AND dateOfOrder < ?;";
//
//    InfographicsResponse response = new InfographicsResponse();
//
//    // DELIVERED
//    jdbcTemplate.query(deliveredQuery, new RowMapper<Void>() {
//      @Override
//      public Void mapRow(ResultSet resultSet, int i) throws SQLException {
//        response.setDeliveredQuantity(resultSet.getInt("deliveredQuantity"));
//        response.setDeliveredTotalPrice(resultSet.getBigDecimal("deliveredTotalPrice"));
//        return null;
//      }
//    }, startDate, endDate);
//
//    // WAITING
//    jdbcTemplate.query(waitingQuery, new RowMapper<Void>() {
//      @Override
//      public Void mapRow(ResultSet resultSet, int i) throws SQLException {
//        response.setWaitingQuantity(resultSet.getInt("waitingQuantity"));
//        response.setWaitingTotalPrice(resultSet.getBigDecimal("waitingTotalPrice"));
//        return null;
//      }
//    }, startDate, endDate);
//
//    // CURRENT
//    response.setCurrentDayTotalPrice(jdbcTemplate.queryForObject(currentDayQuery, BigDecimal.class, endDate));
//    response.setCurrentMonthTotalPrice(jdbcTemplate.queryForObject(currentMonthQuery, BigDecimal.class, endDate.withDayOfMonth(1)));
//    response.setCurrentYearTotalPrice(jdbcTemplate.queryForObject(currentYearQuery, BigDecimal.class, endDate.withDayOfYear(1)));
//
//    // PREVIOUS
//    if (startDate != null) {
//      response.setPrevDayTotalPrice(jdbcTemplate.queryForObject(prevDayQuery, BigDecimal.class, startDate));
//      response.setPrevMonthTotalPrice(jdbcTemplate.queryForObject(prevMonthQuery, BigDecimal.class, startDate, endDate.withDayOfMonth(1)));
//      response.setPrevYearTotalPrice(jdbcTemplate.queryForObject(prevYearQuery, BigDecimal.class, startDate.withDayOfYear(1), endDate.withDayOfYear(1)));
//    } else {
//      // If startDate is null, set all previous values to zero
//      response.setPrevDayTotalPrice(BigDecimal.ZERO);
//      response.setPrevMonthTotalPrice(BigDecimal.ZERO);
//      response.setPrevYearTotalPrice(BigDecimal.ZERO);
//    }
//
//    return response;
//  }

    //------------------------------------------4
//
//      String deliveredQuery = "SELECT SUM(quantity) AS deliveredQuantity, SUM(totalPrice) AS deliveredTotalPrice FROM Order WHERE status = 'DELIVERED' AND dateOfOrder = CURRENT_DATE;";
//      String waitingQuery = "SELECT SUM(quantity) AS waitingQuantity, SUM(totalPrice) AS waitingTotalPrice FROM Order WHERE status = 'WAITING' AND dateOfOrder = CURRENT_DATE;";
//
//      String currentDayQuery = "SELECT SUM(totalPrice) AS currentDayTotalPrice FROM Order WHERE dateOfOrder = CURRENT_DATE;";
//      String prevDayQuery = "SELECT SUM(totalPrice) AS prevDayTotalPrice FROM Order WHERE dateOfOrder = CURRENT_DATE - INTERVAL 1 DAY;";
//
//      String currentMonthQuery = "SELECT SUM(totalPrice) AS currentMonthTotalPrice FROM Order WHERE MONTH(dateOfOrder) = MONTH(CURRENT_DATE);";
//      String prevMonthQuery = "SELECT SUM(totalPrice) AS prevMonthTotalPrice FROM Order WHERE MONTH(dateOfOrder) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH);";
//
//      String currentYearQuery = "SELECT SUM(totalPrice) AS currentYearTotalPrice FROM Order WHERE YEAR(dateOfOrder) = YEAR(CURRENT_DATE);";
//      String prevYearQuery = "SELECT SUM(totalPrice) AS prevYearTotalPrice FROM Order WHERE YEAR(dateOfOrder) = YEAR(CURRENT_DATE - INTERVAL 1 YEAR);";
//
//      if ("day".equalsIgnoreCase(period)) {
//        currentMonthQuery = null;
//        prevMonthQuery = null;
//        currentYearQuery = null;
//        prevYearQuery = null;
//      } else if ("month".equalsIgnoreCase(period)) {
//        currentDayQuery = null;
//        prevDayQuery = null;
//        currentYearQuery = null;
//        prevYearQuery = null;
//      } else if ("year".equalsIgnoreCase(period)) {
//        currentDayQuery = null;
//        prevDayQuery = null;
//        currentMonthQuery = null;
//        prevMonthQuery = null;
//      } else {
//        // Если period не соответствует ни 'day', ни 'month', ни 'year', вернуть пустой ответ
//        return new InfographicsResponse();
//      }
//
//      Map<String, Object> deliveredResult = jdbcTemplate.queryForMap(deliveredQuery);
//      Map<String, Object> waitingResult = jdbcTemplate.queryForMap(waitingQuery);
//      BigDecimal currentDayTotalPrice =
//          currentDayQuery != null ? jdbcTemplate.queryForObject(currentDayQuery, BigDecimal.class)
//              : null;
//      BigDecimal prevDayTotalPrice =
//          prevDayQuery != null ? jdbcTemplate.queryForObject(prevDayQuery, BigDecimal.class) : null;
//      BigDecimal currentMonthTotalPrice =
//          currentMonthQuery != null ? jdbcTemplate.queryForObject(currentMonthQuery,
//              BigDecimal.class) : null;
//      BigDecimal prevMonthTotalPrice =
//          prevMonthQuery != null ? jdbcTemplate.queryForObject(prevMonthQuery, BigDecimal.class)
//              : null;
//      BigDecimal currentYearTotalPrice =
//          currentYearQuery != null ? jdbcTemplate.queryForObject(currentYearQuery, BigDecimal.class)
//              : null;
//      BigDecimal prevYearTotalPrice =
//          prevYearQuery != null ? jdbcTemplate.queryForObject(prevYearQuery, BigDecimal.class)
//              : null;
//
//      int deliveredQuantity = ((Number) deliveredResult.get("deliveredQuantity")).intValue();
//      BigDecimal deliveredTotalPrice = (BigDecimal) deliveredResult.get("deliveredTotalPrice");
//      int waitingQuantity = ((Number) waitingResult.get("waitingQuantity")).intValue();
//      BigDecimal waitingTotalPrice = (BigDecimal) waitingResult.get("waitingTotalPrice");
//
//      return new InfographicsResponse(
//          deliveredTotalPrice, waitingTotalPrice,
//          currentDayTotalPrice, prevDayTotalPrice,
//          currentMonthTotalPrice, prevMonthTotalPrice,
//          currentYearTotalPrice, prevYearTotalPrice
//      );
    return null;
  }

}