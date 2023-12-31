package peaksoft.house.gadgetariumb9.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.house.gadgetariumb9.dto.response.brand.BrandResponse;
import peaksoft.house.gadgetariumb9.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

  Boolean existsByName(String name);

  @Query("select new peaksoft.house.gadgetariumb9.dto.response.brand.BrandResponse(b.id,b.name,b.image) from Brand  b")
  List<BrandResponse> getAllBrands();

}