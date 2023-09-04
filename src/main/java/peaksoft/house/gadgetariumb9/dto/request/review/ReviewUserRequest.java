package peaksoft.house.gadgetariumb9.dto.request.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewUserRequest {

  private Long reviewId;

  private String comment;

  private int grade;

  private String imageLink;
}
