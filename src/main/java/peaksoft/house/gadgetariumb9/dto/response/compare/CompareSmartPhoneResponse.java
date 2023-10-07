package peaksoft.house.gadgetariumb9.dto.response.compare;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
public class CompareSmartPhoneResponse extends CompareProductResponse{

  private int simCard;

  private String diagonalScreen;

  private String batteryCapacity;

  private double screenSize;

}