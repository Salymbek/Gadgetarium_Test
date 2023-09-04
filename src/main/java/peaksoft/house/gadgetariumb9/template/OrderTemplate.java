package peaksoft.house.gadgetariumb9.template;

import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.order.OrderHistoryResponse;
import peaksoft.house.gadgetariumb9.dto.response.order.OrderInfoResponse;
import peaksoft.house.gadgetariumb9.dto.response.order.OrderPaginationAdmin;
import java.time.LocalDate;

public interface OrderTemplate {

  OrderPaginationAdmin getAllOrderAdmin(String status,LocalDate startDate,LocalDate endDate, int pageSize, int pageNumber);

  OrderInfoResponse getOrderInfo(Long orderId);

  List<OrderHistoryResponse> getOrdersByUserId(Long userId);
}