package peaksoft.house.gadgetariumb9.repositories;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.house.gadgetariumb9.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // Метод для получения общей цены и количества выкупов с заданным статусом 'DELIVERED'
  @Query("select sum(o.quantity) from Order o where o.status = 'DELIVERED' and o.dateOfOrder = current_date")
  int getDeliveredQuantity();

  @Query("select sum(o.totalPrice) from Order o where o.status = 'DELIVERED'and o.dateOfOrder = current_date")
  BigDecimal getDeliveredTotalPrice();


  // Метод для получения общей цены и количества заказов с заданным статусом 'WAITING'
  @Query("select sum(o.quantity) from Order o where o.status = in ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY') and o.dateOfOrder = current_date")
  int getOrderQuantity();

  @Query("select sum(o.totalPrice) from Order o where o.status = in ('EXPECTATION','PROCESSING','COURIER_ON_THE_WAY','READY_FOR_DELIVERY') and o.dateOfOrder = current_date")
  BigDecimal getOrderTotalPrice();
  // Метод для получения общей цены за текущий день




//-----------------------------------------------------------------------------------------------
  //@Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.dateOfOrder = CURRENT_DATE")
@Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_of_order between date(now()) and date(now()) + interval '1' day")
BigDecimal getCurrentDayTotalPrice();

  // Метод для получения общей цены за текущий месяц
  @Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_or_oder between date_trunc('month', now()) and date_trunc('month', now()) + interval '1' MONTH - interval '1' second")
  BigDecimal getCurrentMonthTotalPrice();

  // Метод для получения общей цены за текущий год
  @Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_of_order between date_trunc('year', now()) and date_trunc('year', now()) + interval '1' year - interval '1' second")
  BigDecimal getCurrentYearTotalPrice();




  // Метод для получения общей цены за предыдущий день
  @Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_of_order between date(now()) and date(now()) + interval '1' day")
  BigDecimal getPrevDayTotalPrice();

  // Метод для получения общей цены за предыдущий месяц
  @Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_of_order between date_trunc('month', now() - interval '1' month) and date_trunc('month', now()) - interval '1' second")
  BigDecimal getPrevMonthTotalPrice();

  // Метод для получения общей цены за предыдущий год
  @Query(nativeQuery = true, value = "select sum(o.total_price) from orders o where o.status like 'DELIVERED' and o.date_of_order between date_trunc('year', now() - INTERVAL '1' year) and date_trunc('year', now()) - interval '1' second")
  BigDecimal getPrevYearTotalPrice();



//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN quantity ELSE 0 END) AS deliveredQuantity, " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS deliveredTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN quantity ELSE 0 END) AS waitingQuantity, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE dateOfOrder = CURRENT_DATE", nativeQuery = true)
//  InfographicsResponse getCurrentDayStatistics();
//
//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN quantity ELSE 0 END) AS deliveredQuantity, " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS deliveredTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN quantity ELSE 0 END) AS waitingQuantity, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE dateOfOrder = CURRENT_DATE - INTERVAL 1 DAY", nativeQuery = true)
//  InfographicsResponse getPrevDayStatistics();
//
//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS currentMonthTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE MONTH(dateOfOrder) = MONTH(CURRENT_DATE)", nativeQuery = true)
//  InfographicsResponse getCurrentMonthStatistics();
//
//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS prevMonthTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE MONTH(dateOfOrder) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)", nativeQuery = true)
//  InfographicsResponse getPrevMonthStatistics();
//
//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS currentYearTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE YEAR(dateOfOrder) = YEAR(CURRENT_DATE)", nativeQuery = true)
//  InfographicsResponse getCurrentYearStatistics();
//
//  @Query(value = "SELECT " +
//      "SUM(CASE WHEN status = 'DELIVERED' THEN totalPrice ELSE 0 END) AS prevYearTotalPrice, " +
//      "SUM(CASE WHEN status = 'WAITING' THEN totalPrice ELSE 0 END) AS waitingTotalPrice " +
//      "FROM Order WHERE YEAR(dateOfOrder) = YEAR(CURRENT_DATE - INTERVAL 1 YEAR)", nativeQuery = true)
//  InfographicsResponse getPrevYearStatistics();

}