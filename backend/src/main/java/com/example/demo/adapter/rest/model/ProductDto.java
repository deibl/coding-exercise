package com.example.demo.adapter.rest.model;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductDto implements Serializable {

  private UUID domainId;
  private String name;
  private float price;
  private CurrencyDto currency;

}
