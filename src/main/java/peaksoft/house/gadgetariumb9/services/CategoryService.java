package peaksoft.house.gadgetariumb9.services;

import java.util.List;
import peaksoft.house.gadgetariumb9.dto.response.category.SubCategoryResponse;
import peaksoft.house.gadgetariumb9.models.Category;

public interface CategoryService {

  List<Category> getAllCategories();

  List<SubCategoryResponse> getAllSubCategories(Long categoryId);
}
