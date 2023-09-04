package peaksoft.house.gadgetariumb9.template;

import java.time.LocalDate;
import java.util.List;
import peaksoft.house.gadgetariumb9.dto.request.subProduct.SubProductCatalogRequest;
import peaksoft.house.gadgetariumb9.dto.response.compare.CompareProductResponse;
import peaksoft.house.gadgetariumb9.dto.response.compare.ComparisonCountResponse;
import peaksoft.house.gadgetariumb9.dto.response.compare.LatestComparison;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.InfographicsResponse;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.MainPagePaginationResponse;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductHistoryResponse;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductPagination;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductPaginationCatalogAdminResponse;

public interface SubProductTemplate {

  SubProductPagination getProductFilter(SubProductCatalogRequest subProductCatalogRequest, int pageSize, int pageNumber);

  InfographicsResponse infographics(String period);

  List<SubProductHistoryResponse> getRecentlyViewedProducts();


  List<CompareProductResponse> getCompareParameters(String productName);

  List<ComparisonCountResponse> countCompareUser();

  SubProductPaginationCatalogAdminResponse getGetAllSubProductAdmin(String productType, LocalDate startDate, LocalDate endDate, int pageSize, int pageNumber);

  List<LatestComparison> getLatestComparison();
}