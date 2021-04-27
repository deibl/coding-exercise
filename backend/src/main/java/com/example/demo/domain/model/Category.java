package com.example.demo.domain.model;

import java.util.UUID;
import lombok.Data;

@Data
public class Category {

  private UUID domainId;
  private String name;
  private UUID parentId;

}
