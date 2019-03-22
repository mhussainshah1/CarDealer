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

    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category("S-U-V");
        Car car = new Car("Nissan","Alto",2008, "35 miles/gallon",20000,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553093077/java-bootcamp/nsgop8vivttqeqkkkynr.jpg",category);
        category.getCars().add(car);
        categoryRepository.save(category);
        carRepository.save(car);

        category = new Category("Full Size");
        Car car1 = new Car("Honda","Accord",2019,"35 miles/gallon",45000.55,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552081999/java-bootcamp/bo1q7fwi8qytkxi6yyus.jpg",category);
        category.getCars().add(car1);
        Car car2 = new Car("Toyota","Camry",2018,"35 miles/gallon",32000.02,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552100700/java-bootcamp/mj2tywvdhsq0mleow3rp.png",category);
        category.getCars().add(car2);
        categoryRepository.save(category);
        carRepository.save(car1);
        carRepository.save(car2);

        category = new Category("Luxury");
        car = new Car("Mercedez","Benz",2019,"35 miles/gallon", 50000.85,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552162481/java-bootcamp/klztzsqldrhak8lvs7zu.png",category);
        category.getCars().add(car);
        categoryRepository.save(category);
        carRepository.save(car);
    }
}
