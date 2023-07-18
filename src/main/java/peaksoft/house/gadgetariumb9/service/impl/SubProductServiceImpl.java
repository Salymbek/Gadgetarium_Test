package peaksoft.house.gadgetariumb9.service.impl;

import org.springframework.http.HttpStatus;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.service.SubProductService;

public class SubProductServiceImpl implements SubProductService {

  @Override
  public SimpleResponse saveSubProduct(ProductRequest productRequest) {
    return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .build();
  }
}
