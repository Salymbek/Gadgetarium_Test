//package peaksoft.house.gadgetariumb9.template.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.core.JdbcTemplate;
//import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;
//import peaksoft.house.gadgetariumb9.exception.NotFoundException;
//import peaksoft.house.gadgetariumb9.service.OrderService;
//import peaksoft.house.gadgetariumb9.template.JdbcTemplateRepository;
//
//@RequiredArgsConstructor
//@Slf4j
//public class JdbcTemplateImpl implements JdbcTemplateRepository{
//  private final JdbcTemplateRepository jdbcTemplateRepository;
//  private final JdbcTemplate jdbcTemplate;
//  private final OrderService service;
//
//  @Override
//  public InfographicsResponse info(String period) {
//    log.info("Получаем инфографику!");
//    String sql = """
//        SELECT COALESCE(SUM(CASE WHEN o.status = 'DELIVERED' THEN total_price END), 0) AS delivered_total_price,
//               COALESCE(COUNT(CASE WHEN o.status = 'DELIVERED' THEN total_price END), 0) AS delivered_quantity,
//               COALESCE( SUM(CASE WHEN o.status = 'EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY' THEN total_price END), 0) AS waiting_total_price,
//               COALESCE( COUNT(CASE WHEN o.status = 'EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY' THEN total_price END), 0) AS waiting_quantity,
//               COALESCE(SUM(CASE
//                       WHEN o.date_of_order = current_date and 'day' = ? THEN total_price
//                       WHEN extract(year from o.date_of_order) = extract(year from current_date) and
//                         extract(month from o.date_of_order) = extract(month from current_date) and 'month' = ? THEN total_price
//                       WHEN extract(year from o.date_of_order) = extract(year from current_date) and 'year' = ?
//                           THEN total_price END),0)
//               AS current_period,
//               COALESCE(SUM(CASE
//                       WHEN o.date_ofOrder = (current_date - interval '1 day') and 'day' = ? THEN totalPrice
//                       WHEN extract(year from o.dateOfOrder) = extract(year from current_date) and
//                         extract(month from o.dateOfOrder) = extract(month from current_date - interval '1 month') and
//                            'month' = ? THEN totalPrice
//                       WHEN extract(year from o.dateOfOrder) = extract(year from current_date - interval '1 year') and 'year' = ?
//                           THEN totalPrice END),0)
//               AS previous_period
//        FROM orders o;
//        """;
//    return jdbcTemplate.query(sql, (resulSet, i) -> new InfographicsResponse(
//            resulSet.getBigDecimal("delivered_total_price"),
//            resulSet.getInt("delivered_quantity"),
//            resulSet.getBigDecimal("waiting_total_price"),
//            resulSet.getInt("waiting_quantity"),
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
//  }
//}
