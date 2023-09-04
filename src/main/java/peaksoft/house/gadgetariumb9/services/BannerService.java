package peaksoft.house.gadgetariumb9.services;

import java.util.List;
import peaksoft.house.gadgetariumb9.dto.request.banner.BannerRequest;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.models.Banner;

public interface BannerService {
  SimpleResponse saveBanners(BannerRequest bannerRequest);

  List<Banner> getAllBanner();
}