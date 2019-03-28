package com.example.demo.web.controller;

import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.CarRepository;
import com.example.demo.business.entities.repositories.CategoryRepository;
import com.example.demo.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("categories", categoryRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("password") String pw){
        System.out.println("pw: " + pw);
        if(result.hasErrors()){
//            model.addAttribute("user", user);
            model.addAttribute("categories", categoryRepository.findAll());
            return "register";
        } else {
            user.encode(pw);
            userService.saveUser(user);
            model.addAttribute("message", "New User Account Created");
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "login";
    }

}
