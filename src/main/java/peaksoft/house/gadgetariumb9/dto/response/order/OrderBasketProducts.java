package peaksoft.house.gadgetariumb9.dto.response.order;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBasketProducts {

  private String productName;

  private int articleNumber;

  private int quantity;

  private String codeColor;

}
