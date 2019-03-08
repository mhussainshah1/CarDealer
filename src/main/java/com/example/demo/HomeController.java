package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Site Functionality:
 *
 * Add categories
 * Add cars with photos
 * When adding cars, assign a category to that car
 * Update photos of cars
 * Update car information
 * Delete cars
 * View car details
 * List cars that were added
 * List categories
 * When the user clicks on a category, they will be given a list of cars under that category
 */
@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCategories(Model model){
       /* Set<Car> cars = new HashSet<>();
        Car car = new Car("Nissan","Alto",2008, 20000,"/img/nissan-suv.png");
        cars.add(car);
        Category category = new Category("SUV",cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Honda","Accord",2019,45000.55,"/img/2018-Honda-accord.jpg");
        cars.add(car);
        car = new Car("Toyota","Camry",2018,32000.02,"/img/2018-Toyota-Camry.jpg");
        cars.add(car);
        category = new Category("Full Size", cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Mercedez","Benz",2019, 50000.85,"/img/mercedes-benz.png");
        cars.add(car);
        category = new Category("Luxury", cars);
        categoryRepository.save(category);*/
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home";
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @GetMapping("/addcar")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @PostMapping("/processcategory")
    public String processCategoryForm(@Valid @ModelAttribute("category") Category category
                                 //,BindingResult result
                                     ){
        System.out.println("object = " + category);
        //check for errors on the form
        /*if (result.hasErrors() ){
            return "categoryform";
        }*/
        categoryRepository.save(category);
        return "redirect:/";
    }

    @PostMapping("/processcar")
    public String processCarForm(@Valid @ModelAttribute("car") Car car,
                              //BindingResult result,
                              @RequestParam("file") MultipartFile file) {
        System.out.println("object = " + car);
        //check for errors on the form
        /*if (result.hasErrors() ){
            return "carform";
        }*/

        //if there is a picture path and file is empty then save message
       /* if(car.getPicturePath() != null && file.isEmpty()){
            carRepository.save(car);
            return "redirect:/";
        }*/

        if( file.isEmpty()){
            return "carform";
        }
        Map uploadResult;
        try {
            uploadResult = cloudc.upload(
                    file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/carform";
        }
        String url = uploadResult.get("url").toString();
        car.setPicturePath(url);
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);

        return "redirect:/";
    }

    @RequestMapping("/about")
    public String getAbout() {
        return "about";
    }

    //It is not working
   /* @PostConstruct
    public void fillTables() {
        Set<Car> cars = new HashSet<>();
        Car car = new Car("Nissan","Alto",2008, 20000,"/img/nissan-suv.png");
        cars.add(car);
        Category category = new Category("SUV",cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Honda","Accord",2019,45000.55,"/img/2018-Honda-accord.jpg");
        cars.add(car);
        car = new Car("Toyota","Camry",2018,32000.02,"/img/2018-Toyota-Camry.jpg");
        cars.add(car);
        category = new Category("Full Size", cars);
        categoryRepository.save(category);

        cars = new HashSet<>();
        car = new Car("Mercedez","Benz",2019, 50000.85,"/img/mercedes-benz.png");
        cars.add(car);
        category = new Category("Luxury", cars);
        categoryRepository.save(category);
    }*/
}
