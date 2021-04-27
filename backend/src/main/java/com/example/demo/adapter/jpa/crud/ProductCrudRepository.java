package com.example.demo.adapter.jpa.crud;

import com.example.demo.adapter.jpa.model.ProductEntity;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

  Optional<ProductEntity> findByDomainId(UUID domainId);

  Collection<ProductEntity> findAllByCategoryId(UUID categoryId);
}
