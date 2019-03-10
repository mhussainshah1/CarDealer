package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByCategory_Id(long category_id);
}
