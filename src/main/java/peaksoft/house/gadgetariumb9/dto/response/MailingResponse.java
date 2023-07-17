package peaksoft.house.gadgetariumb9.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailingResponse {
  private String message;
  private boolean status;

}
