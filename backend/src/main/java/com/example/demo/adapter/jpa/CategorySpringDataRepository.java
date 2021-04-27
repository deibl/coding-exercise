package com.example.demo.adapter.jpa;

import com.example.demo.adapter.jpa.crud.CategoryCrudRepository;
import com.example.demo.adapter.jpa.model.CategoryEntity;
import com.example.demo.adapter.jpa.transform.CategoryJpaTransform;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.port.out.CategoryRepository;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategorySpringDataRepository implements CategoryRepository {

  private final CategoryCrudRepository categoryCrudRepository;
  private final CategoryJpaTransform categoryJpaTransform;

  @Override
  public Collection<Category> getAllCategories() {
    return StreamSupport.stream(categoryCrudRepository.findAll().spliterator(), false)
        .map(categoryJpaTransform::from)
        .collect(Collectors.toList());
  }

  @Override
  public void createCategory(Category category) {
    final CategoryEntity categoryEntity = categoryJpaTransform.from(category);
    categoryCrudRepository.save(categoryEntity);
  }

  @Override
  public void updateCategory(Category category) {
    deleteCategory(category.getDomainId());
    createCategory(category);
  }

  @Override
  public void deleteCategory(UUID domainId) {
    final CategoryEntity categoryEntity = categoryCrudRepository.findByDomainId(domainId)
        .orElseThrow(() -> new RuntimeException("Category with domain ID: " + domainId + " not found"));
    categoryCrudRepository.delete(categoryEntity);
  }
}
