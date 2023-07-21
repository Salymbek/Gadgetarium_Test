package peaksoft.house.gadgetariumb9.service.impl;


import java.math.BigDecimal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;
import peaksoft.house.gadgetariumb9.exception.NotFoundException;
import peaksoft.house.gadgetariumb9.repository.OrderRepository;
import peaksoft.house.gadgetariumb9.service.OrderService;
@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public InfographicsResponse info(String period) {
    log.info("Получаем инфографику!");
    String sql = """
                SELECT COALESCE(SUM(CASE WHEN o.status = 'DELIVERED' THEN total_price END), 0) AS delivered_total_price,
                       COALESCE(COUNT(CASE WHEN o.status = 'DELIVERED' THEN total_price END), 0) AS delivered_quantity,
                       COALESCE(SUM(CASE WHEN o.status = 'EXPECTATION' OR o.status = 'PROCESSING' OR o.status = 'COURIER_ON_THE_WAY' OR o.status = 'READY_FOR_DELIVERY' THEN total_price END), 0) AS waiting_total_price,
                       COALESCE(COUNT(CASE WHEN o.status = 'EXPECTATION' OR o.status = 'PROCESSING' OR o.status = 'COURIER_ON_THE_WAY' OR o.status = 'READY_FOR_DELIVERY' THEN total_price END), 0) AS waiting_quantity,
                       COALESCE(SUM(CASE
                               WHEN o.status = 'DELIVERED' and o.date_of_order = current_date and 'day' = ? THEN total_price
                               WHEN o.status = 'DELIVERED' and extract(year from o.date_of_order) = extract(year from current_date) and
                                 extract(month from o.date_of_order) = extract(month from current_date) and 'month' = ? THEN total_price
                               WHEN o.status = 'DELIVERED' and extract(year from o.date_of_order) = extract(year from current_date) and 'year' = ?
                                   THEN total_price END),0)
                       AS current_period,
                       COALESCE(SUM(CASE
                               WHEN o.status = 'DELIVERED' and o.date_of_order = (current_date - interval '1' day) and  'day' = ? THEN total_price
                               WHEN o.status = 'DELIVERED' and extract(year from o.date_of_order) = extract(year from current_date) and
                                 extract(month from o.date_of_order) = extract(month from current_date - interval '1' month) and
                                    'month' = ? THEN total_price
                               WHEN o.status = 'DELIVERED' and extract(year from o.date_of_order) = extract(year from current_date - interval '1' year) and 'year' = ?
                                   THEN total_price END),0)
                       AS previous_period
                FROM orders o;
                """;
    return jdbcTemplate.query(sql, (resulSet, i) -> new InfographicsResponse(
            resulSet.getBigDecimal("delivered_total_price"),
            resulSet.getInt("delivered_quantity"),
            resulSet.getBigDecimal("waiting_total_price"),
            resulSet.getInt("waiting_quantity"),
            resulSet.getBigDecimal("current_period"),
            resulSet.getBigDecimal("previous_period")
        ),
        period,
        period,
        period,
        period,
        period,
        period
    ).stream().findFirst().orElseThrow(() -> {
      log.error("Этот ответ не найден!!");
      throw new NotFoundException("Этот ответ не найден!!");
    });
  }
//    InfographicsResponse infographicsResponse = new InfographicsResponse();
//
//    if ("day".equalsIgnoreCase(period)) {
//      infographicsResponse.setCurrentPeriodTotalPrice(orderRepository.getCurrentDayTotalPrice());
//      infographicsResponse.setPrevPeriodTotalPrice(orderRepository.getPrevDayTotalPrice());
//    } else if ("month".equalsIgnoreCase(period)) {
//      infographicsResponse.setCurrentPeriodTotalPrice(orderRepository.getCurrentMonthTotalPrice());
//      infographicsResponse.setPrevPeriodTotalPrice(orderRepository.getPrevMonthTotalPrice());
//    } else if ("year".equalsIgnoreCase(period)) {
//      infographicsResponse.setCurrentPeriodTotalPrice(orderRepository.getCurrentYearTotalPrice());
//      infographicsResponse.setPrevPeriodTotalPrice(orderRepository.getPrevYearTotalPrice());
//    }
//
//    infographicsResponse.setDeliveredQuantity(orderRepository.getDeliveredQuantity());
//    infographicsResponse.setDeliveredTotalPrice(orderRepository.getDeliveredTotalPrice());
//    infographicsResponse.setWaitingQuantity(orderRepository.getOrderQuantity());
//    infographicsResponse.setWaitingTotalPrice(orderRepository.getOrderTotalPrice());
//    return infographicsResponse;
//  }
}
