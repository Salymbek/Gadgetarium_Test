package peaksoft.house.gadgetariumb9.dto.response.user;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserFavoritesResponse {

  private Long subProductId;

  private Long userId;

  private Long productId;

  private String name;

  private double rating;

  private int countOfReview;

  private BigDecimal price;

  private String image;

  private boolean isBasket;
}