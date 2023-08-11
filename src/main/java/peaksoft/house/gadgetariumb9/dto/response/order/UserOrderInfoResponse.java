package peaksoft.house.gadgetariumb9.dto.response.order;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoResponse {

  private int quantity;
  private int totalDiscount;
  private BigDecimal totalPrice;
  private BigDecimal currentPrice;

  private List<OrderBasketProducts> products;

}
