package peaksoft.house.gadgetariumb9.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.house.gadgetariumb9.dto.response.globalSearch.AdminSearchResponse;
import peaksoft.house.gadgetariumb9.services.GlobalSearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/globalSearch")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@Tag(name = "Global search API", description = "API method global search")
public class GlobalSearchApi {

  private final GlobalSearchService globalSearchService;

  @GetMapping
  @Operation(summary = "Admin search", description = "Search for an admin by article, etc.")
  List<AdminSearchResponse> adminSearch(@RequestParam(value = "keyword", required = false) String keyword) {
    return globalSearchService.search(keyword);
  }
}