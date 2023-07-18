package peaksoft.house.gadgetariumb9.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.request.SubProductRequest;

@Setter
@Getter
public class CreateProductPt2 {
  private ProductRequest productRequest;
  private SubProductRequest subProductRequest;

}
