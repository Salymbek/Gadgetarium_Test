package peaksoft.house.gadgetariumb9.dto.request.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestAdmin {

  private String fullName;

  private int orderNumber;

  private int quantity;

  private BigDecimal totalPrice;

  private String typeDelivery;

  private String status;
}