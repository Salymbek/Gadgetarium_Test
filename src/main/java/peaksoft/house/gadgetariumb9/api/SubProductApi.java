package peaksoft.house.gadgetariumb9.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.service.SubProductService;
import peaksoft.house.gadgetariumb9.service.impl.SubProductServiceImpl;

@RestController
@RequestMapping("/subProduct")
public class SubProductApi {

  private final SubProductService subProductService;

  public SubProductApi(SubProductServiceImpl subProductService) {
    this.subProductService = subProductService;
  }

  @PostMapping("/saveSubProduct")
  public SimpleResponse saveSubProduct(@RequestBody ProductRequest productRequest){
    return subProductService.saveSubProduct(productRequest);
  }
}
