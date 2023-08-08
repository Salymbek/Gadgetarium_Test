package peaksoft.house.gadgetariumb9.services.serviceImpl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.response.globalSearch.AdminSearchResponse;
import peaksoft.house.gadgetariumb9.services.GlobalSearchService;
import peaksoft.house.gadgetariumb9.template.GlobalSearchTemplate;

@Service
@RequiredArgsConstructor
public class GlobalSearchServiceImpl implements GlobalSearchService {

  private final GlobalSearchTemplate globalSearchTemplate;

  @Override
  public List<AdminSearchResponse> search(String keyword) {
    return globalSearchTemplate.search(keyword);
  }
}
