package com.example.demo.adapter.rest.model;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class CategoryDto implements Serializable {

  private UUID domainId;
  private String name;
  private UUID parentId;

}
