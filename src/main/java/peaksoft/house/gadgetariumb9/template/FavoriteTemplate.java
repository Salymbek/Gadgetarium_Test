package peaksoft.house.gadgetariumb9.template;

import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductResponse;
import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.user.UserFavoritesResponse;

public interface FavoriteTemplate {

  List<SubProductResponse> getAllFavorite();

  List<UserFavoritesResponse> getAllFavoriteByUserId(Long userId);
}