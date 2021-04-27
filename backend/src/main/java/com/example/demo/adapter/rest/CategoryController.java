package com.example.demo.adapter.rest;

import com.example.demo.adapter.rest.model.CategoryDto;
import com.example.demo.adapter.rest.transform.CategoryRestTransform;
import com.example.demo.domain.port.in.CategoryUseCase;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
public class CategoryController {

  private final CategoryRestTransform categoryRestTransform;
  private final CategoryUseCase categoryUseCase;

  @GetMapping("/categories")
  public ResponseEntity<Collection<CategoryDto>> getAllCategories() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            categoryUseCase.getAllCategories()
                .stream()
                .map(categoryRestTransform::from)
                .collect(Collectors.toList())
        );
  }

  @PostMapping("/categories")
  public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto) {
    categoryUseCase.createCategory(categoryRestTransform.from(categoryDto));
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

  @PutMapping("/categories/{domainId}")
  public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable UUID domainId) {
    categoryUseCase.updateCategory(categoryRestTransform.from(categoryDto));
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

  @DeleteMapping("/categories/{domainId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID domainId) {
    categoryUseCase.deleteCategory(domainId);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }
}
