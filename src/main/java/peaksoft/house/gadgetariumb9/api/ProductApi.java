package peaksoft.house.gadgetariumb9.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.CreateProductPt2;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.service.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products")
public class ProductApi {

  private final ProductService productService;

  @PostMapping("/save")
//  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @Operation(summary = "save Product", description = "save")
  public SimpleResponse save(@RequestBody ProductRequest productRequest) {
    return productService.saveProduct(productRequest);
  }

//  @PostMapping("/products")
////  @Operation(summary = "save Product", description = "save")
//  public SimpleResponse saveProductPart2(@RequestBody CreateProductPt2 createProductPt2) {
//    return productService.saveProductPart2(createProductPt2.getProductRequest(), createProductPt2.getSubProductRequest());
//  }
}