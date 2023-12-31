package peaksoft.house.gadgetariumb9.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.house.gadgetariumb9.dto.request.banner.BannerRequest;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.models.Banner;
import peaksoft.house.gadgetariumb9.services.serviceImpl.BannerServiceImpl;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
@Tag(name = "Banner API", description = "API for banner management")
public class BannerApi {

  private final BannerServiceImpl bannerService;

  @PostMapping()
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Save Banner", description = "This is a method of saving banners")
  SimpleResponse saveBanners(@RequestBody @Valid BannerRequest bannerRequest) {
    return bannerService.saveBanners(bannerRequest);
  }

  @PermitAll
  @Operation(summary = "Get all banners", description = "Retrieve a list of all banners.")
  @GetMapping
  List<Banner> getAllBanners() {
    return bannerService.getAllBanner();
  }
}