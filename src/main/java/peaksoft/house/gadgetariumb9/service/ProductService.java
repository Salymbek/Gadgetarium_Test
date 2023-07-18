package peaksoft.house.gadgetariumb9.service;

import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.request.SubProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;

public interface ProductService {
  SimpleResponse saveProduct(ProductRequest productRequest);
 // SimpleResponse saveProductPart2(ProductRequest productRequest, SubProductRequest subProductRequest);
  //SimpleResponse saveProductPart3(ProductRequest productRequest);
}
