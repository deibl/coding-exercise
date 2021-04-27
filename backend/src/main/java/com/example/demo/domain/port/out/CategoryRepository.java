package com.example.demo.domain.port.out;

import com.example.demo.domain.model.Category;
import java.util.Collection;
import java.util.UUID;

public interface CategoryRepository {

  Collection<Category> getAllCategories();

  void createCategory(Category category);

  void updateCategory(Category category);

  void deleteCategory(UUID domainId);
}
