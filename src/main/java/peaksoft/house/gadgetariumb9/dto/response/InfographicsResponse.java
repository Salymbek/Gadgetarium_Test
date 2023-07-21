package peaksoft.house.gadgetariumb9.dto.response;

import java.math.BigDecimal;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class InfographicsResponse {
    private BigDecimal deliveredTotalPrice;
    private int deliveredQuantity;
    private BigDecimal waitingTotalPrice;
    private int waitingQuantity;
    private BigDecimal currentPeriod;
    private BigDecimal previousPeriod;

    public InfographicsResponse(BigDecimal deliveredTotalPrice, int deliveredQuantity,
        BigDecimal waitingTotalPrice, int waitingQuantity, BigDecimal currentPeriod,
        BigDecimal previousPeriod) {
        this.deliveredTotalPrice = deliveredTotalPrice;
        this.deliveredQuantity = deliveredQuantity;
        this.waitingTotalPrice = waitingTotalPrice;
        this.waitingQuantity = waitingQuantity;
        this.currentPeriod = currentPeriod;
        this.previousPeriod = previousPeriod;
    }

    //    private BigDecimal currentYearTotalPrice;
//    private BigDecimal prevDayTotalPrice;
//    private BigDecimal prevMonthTotalPrice;
//    private BigDecimal prevYearTotalPrice;

//    public InfographicsResponse(BigDecimal deliveredTotalPrice, BigDecimal waitingTotalPrice, BigDecimal currentDayTotalPrice, BigDecimal prevDayTotalPrice, BigDecimal currentMonthTotalPrice, BigDecimal prevMonthTotalPrice, BigDecimal currentYearTotalPrice,
//        BigDecimal prevYearTotalPrice) {
//    }

//    private BigDecimal ransomPrice;
//    private int countOfRansoms;
//    private BigDecimal orderPrice;
//    private int countOfOrders;
//    private BigDecimal currentPeriod;
//    private BigDecimal previousPeriod;
//
//    public InfographicsResponse(BigDecimal ransomPrice, int countOfRansoms, BigDecimal orderPrice, int countOfOrders, BigDecimal currentPeriod, BigDecimal previousPeriod) {
//    }
}
