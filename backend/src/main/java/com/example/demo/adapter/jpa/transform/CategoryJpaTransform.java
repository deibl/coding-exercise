package com.example.demo.adapter.jpa.transform;

import com.example.demo.adapter.jpa.model.CategoryEntity;
import com.example.demo.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryJpaTransform {

  @Mapping(target = "id", ignore = true)
  CategoryEntity from(Category category);

  Category from(CategoryEntity categoryEntity);

}
