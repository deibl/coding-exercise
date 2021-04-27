package com.example.demo.domain.service;

import com.example.demo.domain.model.Category;
import com.example.demo.domain.port.in.CategoryUseCase;
import com.example.demo.domain.port.out.CategoryRepository;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService implements CategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Override
  public Collection<Category> getAllCategories() {
    return categoryRepository.getAllCategories();
  }

  @Override
  public void createCategory(Category category) {
    categoryRepository.createCategory(category);
  }

  @Override
  public void updateCategory(Category category) {
    categoryRepository.updateCategory(category);
  }

  @Override
  public void deleteCategory(UUID domainId) {
    categoryRepository.deleteCategory(domainId);
  }
}
