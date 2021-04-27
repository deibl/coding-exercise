package com.example.demo.adapter.rest;

import com.example.demo.adapter.rest.model.ProductDto;
import com.example.demo.adapter.rest.transform.ProductRestTransform;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.port.in.ProductUseCase;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
public class ProductController {

  private final ProductUseCase productUseCase;
  private final ProductRestTransform productRestTransform;

  @GetMapping("/products")
  public ResponseEntity<Collection<ProductDto>> getProductsByCategory(@RequestParam UUID categoryDomainId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            productUseCase.getProductsByCategory(categoryDomainId)
                .stream()
                .map(productRestTransform::from)
                .collect(Collectors.toList())
        );
  }

  @PostMapping("/products")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
      @RequestParam UUID categoryDomainId) {
    final Product product = productUseCase.createProduct(productRestTransform.from(productDto), categoryDomainId);
    return ResponseEntity.ok(productRestTransform.from(product));
  }

  @PutMapping("/products/{domainId}")
  public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable UUID domainId) {
    final Product product = productUseCase.updateProduct(productRestTransform.from(productDto));
    return ResponseEntity.ok(productRestTransform.from(product));
  }

  @DeleteMapping("/products/{domainId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable UUID domainId) {
    productUseCase.deleteProduct(domainId);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }
}
