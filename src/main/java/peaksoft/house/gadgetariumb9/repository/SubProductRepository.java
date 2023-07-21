package peaksoft.house.gadgetariumb9.repository;

import jakarta.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.house.gadgetariumb9.entities.SubProduct;

public interface SubProductRepository extends JpaRepository<SubProduct, Long> {
  //---------------------------------------Ransoms--------------------------------------------------------

//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = 'DELIVERED' AND o.dateOfOrder = :date")
//  Tuple getRansomsSummaryByDay(@Param("date") LocalDate date);


//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = 'DELIVERED' AND YEAR(o.dateOfOrder) = :year AND MONTH(o.dateOfOrder) = :month")
//  Tuple getRansomsSummaryByMonth(@Param("year") int year, @Param("month") int month);
//
//
//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = 'DELIVERED' AND YEAR(o.dateOfOrder) = :year")
//  Tuple getRansomsSummaryByYear(@Param("year") int year);
//----------------------------------------Oders-----------------------------------------
//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = in ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY') AND o.dateOfOrder = :date")
//  Tuple getOrdersSummaryByDay(@Param("date") LocalDate date);
//

//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = in ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY') AND YEAR(o.dateOfOrder) = :year AND MONTH(o.dateOfOrder) = :month")
//  Tuple getOrdersSummaryByMonth(@Param("year") int year, @Param("month") int month);
//
//
//  @Query("SELECT SUM(o.quantity) AS totalQuantity, SUM(o.totalPrice) AS totalTotalPrice " +
//      "FROM SubProduct sp JOIN sp.orders o " +
//      "WHERE o.status = in ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY') AND YEAR(o.dateOfOrder) = :year")
//  Tuple getOrdersSummaryByYear(@Param("year") int year);
//----------------------------------------All summary----------------------------------------------------







//  @Query("select sum (o.totalPrice) from SubProduct sp join sp.orders o where o.status = 'DELIVERED' and o.dateOfOrder between  date(now()) and date(now()) + interval 1 day")
//  BigDecimal totalRansomPrice();
//  @Query("select sum (o.quantity) from SubProduct sp join sp.orders o where o.status = ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY')")
//  Long countOrderPrice();
//  @Query("select sum (o.totalPrice) from SubProduct sp join sp.orders o where o.status = ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY')")
//  BigDecimal totalOrderPrice();
//
//
//
//  @Query("select sum(o.totalPrice) from SubProduct sp join sp.orders o where o.status like 'DELIVERED' and o.dateOfOrder between date(now()) and date(now()) + interval '1' day")
//  long countCurrentByDay();
//
//  @Query("SELECT sum (s.price * o.quantity) FROM SubProduct s JOIN s.orders o WHERE DATE_TRUNC('month', o.dateOfOrder) = DATE_TRUNC('month', CURRENT_DATE)")
//  BigDecimal getCurrentPriceByMonth();
//
//  @Query("SELECT COUNT(o) FROM SubProduct s JOIN s.orders o WHERE DATE_TRUNC('year', o.dateOfOrder) = DATE_TRUNC('year', CURRENT_DATE)")
//  int getOrderCountByYear();
}