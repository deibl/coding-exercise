package com.example.demo.integration.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class CategoryDto {

  @JsonProperty("domainId")
  private UUID domainId;
  @JsonProperty("name")
  private String name;
  @JsonProperty("parentId")
  private UUID parentId;

  public CategoryDto(UUID domainId, String name, UUID parentId) {
    this.domainId = domainId;
    this.name = name;
    this.parentId = parentId;
  }

  public UUID getDomainId() {
    return domainId;
  }

  public String getName() {
    return name;
  }

  public UUID getParentId() {
    return parentId;
  }

  public void setDomainId(UUID domainId) {
    this.domainId = domainId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentId(UUID parentId) {
    this.parentId = parentId;
  }
}
