package com.example.demo.adapter.rest.transform;

import com.example.demo.adapter.rest.model.CategoryDto;
import com.example.demo.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRestTransform {

  CategoryDto from(Category category);

  Category from(CategoryDto categoryDto);

}
