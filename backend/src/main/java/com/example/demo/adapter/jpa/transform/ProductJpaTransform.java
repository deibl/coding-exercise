package com.example.demo.adapter.jpa.transform;

import com.example.demo.adapter.jpa.model.ProductEntity;
import com.example.demo.domain.model.Product;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductJpaTransform {

  @Mapping(target = "domainId", source = "product.domainId")
  @Mapping(target = "name", source = "product.name")
  @Mapping(target = "price", source = "product.price")
  @Mapping(target = "categoryId", source = "categoryId")
  @Mapping(target = "id", ignore = true)
  ProductEntity from(Product product, UUID categoryId);

  @Mapping(target = "currency", ignore = true)
  Product from(ProductEntity productEntity);

}
