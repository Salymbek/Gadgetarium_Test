package peaksoft.house.gadgetariumb9.services;

import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.globalSearch.AdminSearchResponse;

public interface GlobalSearchService {
  List<AdminSearchResponse> search (String keyword);
}
