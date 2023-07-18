package peaksoft.house.gadgetariumb9.dto.request;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MailingRequest {
  private String name;
  private String description;
  private String image;
  private ZonedDateTime startDate;
  private ZonedDateTime finishDate;
}
