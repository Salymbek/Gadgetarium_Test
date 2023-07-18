package peaksoft.house.gadgetariumb9.service;

import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;

@Service
public interface SubProductService {
  SimpleResponse saveSubProduct (ProductRequest productRequest);

}
