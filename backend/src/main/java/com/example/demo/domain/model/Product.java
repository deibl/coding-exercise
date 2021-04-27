package com.example.demo.domain.model;

import java.util.UUID;
import lombok.Data;

@Data
public class Product {

  private UUID domainId;
  private String name;
  private float price;
  private Currency currency;

}
