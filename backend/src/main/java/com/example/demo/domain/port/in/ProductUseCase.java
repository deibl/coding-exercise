package com.example.demo.domain.port.in;

import com.example.demo.domain.model.Product;
import java.util.Collection;
import java.util.UUID;

public interface ProductUseCase {

  Collection<Product> getProductsByCategory(UUID categoryDomainId);

  Product createProduct(Product product, UUID categoryDomainId);

  Product updateProduct(Product product);

  void deleteProduct(UUID domainId);

}
