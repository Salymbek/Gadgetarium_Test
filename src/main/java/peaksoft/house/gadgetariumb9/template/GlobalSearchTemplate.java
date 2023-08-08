package peaksoft.house.gadgetariumb9.template;

import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.globalSearch.AdminSearchResponse;

public interface GlobalSearchTemplate {

  List<AdminSearchResponse> search (String keyword);
}
