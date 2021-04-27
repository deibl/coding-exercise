package com.example.demo.domain.port.out;

import com.example.demo.domain.model.Product;
import java.util.Collection;
import java.util.UUID;

public interface ProductRepository {

  Collection<Product> getProductsByCategory(UUID categoryDomainId);

  void createProduct(Product product, UUID categoryDomainId);

  void updateProduct(Product product);

  void deleteProduct(UUID domainId);

}
