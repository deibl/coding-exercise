package com.example.demo.adapter.jpa;

import com.example.demo.adapter.jpa.crud.ProductCrudRepository;
import com.example.demo.adapter.jpa.model.ProductEntity;
import com.example.demo.adapter.jpa.transform.ProductJpaTransform;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.port.out.ProductRepository;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@AllArgsConstructor
public class ProductSpringDataRepository implements ProductRepository {

  private final ProductCrudRepository productCrudRepository;
  private final ProductJpaTransform productJpaTransform;

  @Override
  public Collection<Product> getProductsByCategory(UUID categoryDomainId) {
    return productCrudRepository.findAllByCategoryId(categoryDomainId)
        .stream()
        .map(productJpaTransform::from)
        .collect(Collectors.toList());
  }

  @Override
  public void createProduct(@RequestBody Product product, UUID categoryDomainId) {
    final ProductEntity productEntity = productJpaTransform.from(product, categoryDomainId);
    productCrudRepository.save(productEntity);
  }

  @Override
  public void updateProduct(Product product) {
    final ProductEntity productEntity = productCrudRepository.findByDomainId(product.getDomainId())
        .orElseThrow(() -> new RuntimeException("Product with domain ID " + product.getDomainId() + " not found."));
    productEntity.setName(product.getName());
    productEntity.setPrice(product.getPrice());
    productCrudRepository.save(productEntity);
  }

  @Override
  public void deleteProduct(UUID domainId) {
    final ProductEntity productEntity = productCrudRepository.findByDomainId(domainId)
        .orElseThrow(() -> new RuntimeException("Product with domain ID: " + domainId + " not found"));
    productCrudRepository.delete(productEntity);
  }
}
