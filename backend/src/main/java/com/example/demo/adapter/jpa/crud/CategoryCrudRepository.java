package com.example.demo.adapter.jpa.crud;

import com.example.demo.adapter.jpa.model.CategoryEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {

  Optional<CategoryEntity> findByDomainId(UUID domainId);

}
