package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String carForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Car car,
                              BindingResult result,
                              @RequestParam("file") MultipartFile file,
                              Model model){
        System.out.println("object = " + car);
        if(result.hasErrors()){
            for (ObjectError e : result.getAllErrors()){
                System.out.println(e);
            }
            model.addAttribute("categories", categoryRepository.findAll());
            return "carform";
        }

        //if there is a picture path and file is empty then save message
        if(car.getPicturePath() != null && file.isEmpty()){
            carRepository.save(car);
            return "redirect:/";
        }

        if( file.isEmpty()){
            model.addAttribute("categories", categoryRepository.findAll());
            return "carform";
        }

        Map uploadResult;
        try {
            uploadResult = cloudc.upload(
                    file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("categories", categoryRepository.findAll());
            return "redirect:/carform";
        }

        String uploadURL = uploadResult.get("url").toString();
        String uploadedName = uploadResult.get("public_id").toString();
        String transformedImage = cloudc.createUrl(uploadedName,150,150);

        car.setPicturePath(transformedImage);
        carRepository.save(car);
        return "redirect:/";
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping("/processcategory")
    public String processSubject(@Valid Category category,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            for (ObjectError e : result.getAllErrors()){
                System.out.println(e);
            }
            return "category";
        }
        if(categoryRepository.findByTitle(category.getTitle()) != null){
            model.addAttribute("message", "You already have a category called " +
                    category.getTitle() + "!" + " Try something else.");
            return "category";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "about";
    }

    @RequestMapping("/detailcategory/{id}")
    public String showCarsByCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("cars", carRepository.findAllByCategory_Id(id));
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @PostConstruct
    public void fillTables(){
        Category category = new Category();
        category.setTitle("Compact");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Medium Size");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Full Size");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Luxury");
        categoryRepository.save(category);
    }
}









