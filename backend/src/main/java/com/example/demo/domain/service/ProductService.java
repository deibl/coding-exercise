package com.example.demo.domain.service;

import com.example.demo.domain.model.Product;
import com.example.demo.domain.port.in.ProductUseCase;
import com.example.demo.domain.port.out.ProductRepository;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements ProductUseCase {

  private final ProductRepository productRepository;
  private final CurrencyService currencyService;

  @Override
  public Collection<Product> getProductsByCategory(UUID categoryDomainId) {
    final Collection<Product> products = productRepository.getProductsByCategory(categoryDomainId);
    products.forEach(product -> product.setCurrency(currencyService.getDefaultCurrency()));
    return products;
  }

  @Override
  public Product createProduct(Product product, UUID categoryDomainId) {
    convertCurrencyIfRequired(product);
    productRepository.createProduct(product, categoryDomainId);
    return product;
  }

  @Override
  public Product updateProduct(Product product) {
    convertCurrencyIfRequired(product);
    productRepository.updateProduct(product);
    return product;
  }

  @Override
  public void deleteProduct(UUID domainId) {
    productRepository.deleteProduct(domainId);
  }

  private void convertCurrencyIfRequired(Product product) {
    if (currencyService.currencyNeedsToBeConverted(product.getCurrency())) {
      product.setPrice(currencyService.convertToDefaultCurrency(product.getPrice(), product.getCurrency()));
      product.setCurrency(currencyService.getDefaultCurrency()); //just pretending it is persisted
    }
  }
}
