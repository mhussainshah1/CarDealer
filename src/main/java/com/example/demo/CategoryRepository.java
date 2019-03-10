package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  Category findByTitle(String category_name);
}
