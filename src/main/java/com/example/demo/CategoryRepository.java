package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  Category findByTitle(String category_name);
}
