package peaksoft.house.gadgetariumb9.dto.response.product;

import java.math.BigDecimal;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailsResponse {

  private Long productId;

  private Long subProductId;

  private String image;

  private String productName;

  private String brandName;

  private String codeColor;

  private int ram;

  private int rom;

  private int quantity;

  private BigDecimal price;
}