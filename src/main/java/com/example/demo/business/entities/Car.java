package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    private String make;

    @NotNull
    @Size(min=3)
    private String model;

    @NotNull
    private int year;

    @NotNull
    @Size(min=10)
    private String description;

    @NotNull
    @Min(3)
    private double price;

    //@NotNull
    //@Size(min = 4)
    private String picturePath;

    @ManyToOne
    private Category category;

    public Car() {

    }

    public Car(@NotNull @Size(min = 4) String make, @NotNull @Size(min = 3) String model, @NotNull int year, @NotNull @Size(min = 10) String description, @NotNull @Min(3) double price, String picturePath, Category category) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.description = description;
        this.price = price;
        this.picturePath = picturePath;
        this.category = category;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String string = "Make = [" + make +
                ",Model =" + model +
                ",Year = " + year +
                ",Description = "+ description +
                ",Price = " + price +
                ",Picture Path = " + picturePath+
                ",Category = "+category.getTitle()+"]";
        return  string;
    }
}
