package com.example.demo.domain.port.in;

import com.example.demo.domain.model.Category;
import java.util.Collection;
import java.util.UUID;

public interface CategoryUseCase {

  Collection<Category> getAllCategories();

  void createCategory(Category category);

  void updateCategory(Category category);

  void deleteCategory(UUID domainId);

}
