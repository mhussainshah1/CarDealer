package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByCategory_Id(long category_id);
}
