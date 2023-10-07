package peaksoft.house.gadgetariumb9.repositories;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.house.gadgetariumb9.models.Basket;
import java.util.List;

@Transactional
public interface BasketRepository extends JpaRepository<Basket, Long> {
  List<Basket> getBasketByUserId(Long userId);

  @Query("SELECT b FROM Basket b JOIN b.subProducts s WHERE s.id = ?1")
  List<Basket> getBasketBySubProductId(Long subProductId);

  @Query("SELECT b FROM Basket b WHERE b.user.id = :userId")
  Basket findByUserId(@Param("userId") Long userId);

  void deleteById (@NonNull Long basketId);

}