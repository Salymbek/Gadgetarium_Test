package peaksoft.house.gadgetariumb9.services;

import peaksoft.house.gadgetariumb9.dto.request.product.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.request.subProduct.SubProductCatalogRequest;
import peaksoft.house.gadgetariumb9.dto.response.compare.CompareProductResponse;
import peaksoft.house.gadgetariumb9.dto.response.compare.ComparisonCountResponse;
import peaksoft.house.gadgetariumb9.dto.response.compare.LatestComparison;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.*;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import java.time.LocalDate;
import java.util.List;

public interface SubProductService {

  SubProductPagination getSubProductCatalogs(SubProductCatalogRequest subProductCatalogRequest, int pageSize, int pageNumber);

  InfographicsResponse infographics(String period);

  MainPagePaginationResponse getNewProducts(int page, int pageSize);

  MainPagePaginationResponse getRecommendedProducts(int page, int pageSize);

  MainPagePaginationResponse getAllDiscountProducts(int page, int pageSize);

  void addRecentlyViewedProduct(Long productId);

  List<SubProductHistoryResponse> getRecentlyViewedProduct();

  SubProductPaginationCatalogAdminResponse getGetAllSubProductAdmin(String productType, LocalDate startDate, LocalDate endDate, int pageSize, int pageNumber);

  SimpleResponse singleDelete(Long subProductId);

  SimpleResponse multiDelete(List<Long> subProductId);

  SimpleResponse updateSubProduct(Long subProductId, ProductRequest productRequest);

  List<ComparisonCountResponse>countCompareUser();

  SimpleResponse comparisonAddOrDelete(Long id, boolean addOrDelete);

  List<CompareProductResponse> getCompareParameters(String productName);

  SimpleResponse clearUserCompare(List<Long> subProductIds);

  List<LatestComparison> getLatestComparison();

  List<CountColorResponse> getCountColor (Long categoryId);
}