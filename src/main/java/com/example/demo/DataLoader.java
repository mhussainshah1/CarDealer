package com.example.demo;

//change image folder path to cloudinary
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
/*       Set<Car> cars = new HashSet<>();
        Car car = new Car("Nissan","Alto",2008, 20000,"/img/nissan-suv.png");
        cars.add(car);
        Category category = new Category("SUV",cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Honda","Accord",2019,45000.55,"/img/2018-Honda-accord.jpg");
        cars.add(car);
        car = new Car("Toyota","Camry",2018,32000.02,"/img/2018-Toyota-Camry.png");
        cars.add(car);
        category = new Category("Full Size", cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Mercedez","Benz",2019, 50000.85,"/img/mercedes-benz.png");
        cars.add(car);
        category = new Category("Luxury", cars);
        categoryRepository.save(category);*/
    }
}
