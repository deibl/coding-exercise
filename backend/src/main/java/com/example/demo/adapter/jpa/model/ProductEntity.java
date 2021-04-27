package com.example.demo.adapter.jpa.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {

  @Id
  @GeneratedValue
  private Integer id;
  private UUID domainId;
  private String name;
  private float price;
  private UUID categoryId;

}
