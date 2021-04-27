package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.example.demo.integration.client.CategoryDto;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CategoryIntegrationTest {

  @LocalServerPort
  private int localPort;
  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  private RestTemplate restTemplate;

  @PostConstruct
  public void initialize() {
    restTemplate = restTemplateBuilder.rootUri("http://localhost:" + localPort).build();
  }

  @Test
  void createNewCategory() {
    final UUID domainIdNewCategory = UUID.randomUUID();
    final String name = "New Category name";
    final UUID parentId = null;
    final CategoryDto categoryDto = new CategoryDto(domainIdNewCategory, name, parentId);

    restTemplate.postForEntity("/categories/", categoryDto, Void.class);

    final CategoryDto[] categories = restTemplate.getForObject("/categories", CategoryDto[].class);
    assertThat(categories).anyMatch(c -> domainIdNewCategory.equals(c.getDomainId()));
    assertThat(categories)
        .filteredOn(c -> domainIdNewCategory.equals(c.getDomainId()))
        .extracting(CategoryDto::getDomainId, CategoryDto::getName, CategoryDto::getParentId)
        .containsExactly(tuple(domainIdNewCategory, name, parentId));
  }

  @Test
  void deleteCategory() {
    final UUID domainIdCategory = UUID.randomUUID();
    final CategoryDto categoryDto = new CategoryDto(domainIdCategory, "to be delete", null);

    restTemplate.postForEntity("/categories/", categoryDto, Void.class);
    final CategoryDto[] categoriesBeforeDelete = restTemplate.getForObject("/categories", CategoryDto[].class);
    assertThat(categoriesBeforeDelete).anyMatch(c -> domainIdCategory.equals(c.getDomainId()));

    restTemplate.delete("/categories/" + domainIdCategory);

    final CategoryDto[] categoriesAfterDelete = restTemplate.getForObject("/categories", CategoryDto[].class);
    assertThat(categoriesAfterDelete).noneMatch(c -> domainIdCategory.equals(c.getDomainId()));
  }

  @Test
  void updateCategory() {
    final UUID domainIdCategory = UUID.randomUUID();
    final String oldName = "to be update";
    final CategoryDto categoryBeforeUpdate = new CategoryDto(domainIdCategory, oldName, null);

    restTemplate.postForEntity("/categories/", categoryBeforeUpdate, Void.class);
    final CategoryDto[] categoriesBeforeUpdate = restTemplate.getForObject("/categories", CategoryDto[].class);
    assertThat(categoriesBeforeUpdate).anyMatch(c -> domainIdCategory.equals(c.getDomainId()));
    assertThat(categoriesBeforeUpdate)
        .filteredOn(c -> domainIdCategory.equals(c.getDomainId()))
        .extracting(CategoryDto::getName)
        .containsExactly(oldName);

    final String newName = "updated name";
    final CategoryDto categoryAfterUpdate = new CategoryDto(domainIdCategory, newName, null);
    restTemplate.put("/categories/" + domainIdCategory, categoryAfterUpdate);

    final CategoryDto[] categoriesAfterUpdate = restTemplate.getForObject("/categories", CategoryDto[].class);
    assertThat(categoriesAfterUpdate).anyMatch(c -> domainIdCategory.equals(c.getDomainId()));
    assertThat(categoriesAfterUpdate)
        .filteredOn(c -> domainIdCategory.equals(c.getDomainId()))
        .extracting(CategoryDto::getName)
        .containsExactly(newName);
  }
}
