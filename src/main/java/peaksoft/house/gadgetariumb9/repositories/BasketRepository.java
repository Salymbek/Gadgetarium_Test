package peaksoft.house.gadgetariumb9.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.house.gadgetariumb9.models.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

  List<Basket> getBasketByUserId(Long userId);

  @Query("SELECT b FROM Basket b JOIN b.subProducts s WHERE s.id = ?1")
  List<Basket> getBasketBySubProductId(Long subProductId);
}