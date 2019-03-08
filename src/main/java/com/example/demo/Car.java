package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotNull
    private String make;

    //@NotNull
    private String model;

    //@Max(2019)
    private int year;

    //@Min(0)
    private double msrp;//manufacturer's suggested retail price

    //@NotNull
    private String picturePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName="id")
    private Category category;

    public Car() {
    }

    public Car(String make, String model, int year, double msrp, String picturePath) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
        this.picturePath = picturePath;
    }

    public Car(String make, String model, int year, double msrp, String picturePath, Category category) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
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

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
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
}
