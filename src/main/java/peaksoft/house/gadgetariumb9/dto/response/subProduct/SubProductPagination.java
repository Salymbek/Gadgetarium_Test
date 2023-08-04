package peaksoft.house.gadgetariumb9.dto.response.subProduct;

import lombok.*;
import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductCatalogResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubProductPagination {

  private List<SubProductCatalogResponse> responseList;

  private int pageSize;

  private int numberPage;

}
