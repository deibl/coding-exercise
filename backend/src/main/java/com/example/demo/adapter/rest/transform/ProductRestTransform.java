package com.example.demo.adapter.rest.transform;

import com.example.demo.adapter.rest.model.ProductDto;
import com.example.demo.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestTransform {

  ProductDto from(Product product);

  Product from(ProductDto productDto);

}
