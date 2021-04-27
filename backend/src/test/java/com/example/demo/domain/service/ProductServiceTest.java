package com.example.demo.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.example.demo.domain.model.Currency;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.port.out.ProductRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private static final Currency DEFAULT_CURRENCY = new Currency("EUR", "Euro");

  @Mock
  private ProductRepository productRepository;
  @Mock
  private CurrencyService currencyService;
  @InjectMocks
  private ProductService underTest;

  @Test
  void getProductsByCategory_noCategories() {
    final UUID categoryDomainId = UUID.randomUUID();
    given(productRepository.getProductsByCategory(categoryDomainId)).willReturn(Collections.emptyList());

    final Collection<Product> actual = underTest.getProductsByCategory(categoryDomainId);

    assertThat(actual).isEmpty();
  }

  @Test
  void getProductsByCategory_oneCategory() {
    final UUID categoryDomainId = UUID.randomUUID();
    final Product product = new Product();
    given(productRepository.getProductsByCategory(categoryDomainId)).willReturn(List.of(product));
    given(currencyService.getDefaultCurrency()).willReturn(DEFAULT_CURRENCY);

    final Collection<Product> actualProducts = underTest.getProductsByCategory(categoryDomainId);

    assertThat(actualProducts).hasSize(1);
    final Product actualProduct = actualProducts.iterator().next();
    assertThat(actualProduct.getCurrency().getAbbreviation()).isEqualTo(DEFAULT_CURRENCY.getAbbreviation());
  }

  @Test
  void getProductsByCategory_multipleCategories() {
    final UUID categoryDomainId = UUID.randomUUID();
    final Product product1 = new Product();
    final Product product2 = new Product();
    final Product product3 = new Product();
    given(productRepository.getProductsByCategory(categoryDomainId)).willReturn(List.of(product1, product2, product3));
    given(currencyService.getDefaultCurrency()).willReturn(DEFAULT_CURRENCY);

    final Collection<Product> actualProducts = underTest.getProductsByCategory(categoryDomainId);

    assertThat(actualProducts)
        .hasSize(3)
        .extracting(Product::getCurrency)
        .extracting(Currency::getAbbreviation)
        .allMatch(abbreviation -> DEFAULT_CURRENCY.getAbbreviation().equals(abbreviation));
  }
}
