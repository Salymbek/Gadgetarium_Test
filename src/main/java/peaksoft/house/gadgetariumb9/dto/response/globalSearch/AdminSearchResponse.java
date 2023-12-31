package peaksoft.house.gadgetariumb9.dto.response.globalSearch;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminSearchResponse {

  private Long productId;

  private Long subProductId;

  private String image;

  private int articleNumber;

  private String name;

  private LocalDate createdAt;

  private int quantity;

  private BigDecimal price;

  private int sale;

  private BigDecimal currentPrice;

  private double rating;
}