package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 4)
    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "category")
    public Set<Car> cars;

    public Category() {
        cars = new HashSet<>();
    }

    public Category(@NotNull @Size(min = 4) String title) {
        this();
        this.title = title;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        String string = "Name = " + title;
        for (Car car : cars) {
            string += "\nCar - = " + car.toString();
        }
        return string;
    }
}
