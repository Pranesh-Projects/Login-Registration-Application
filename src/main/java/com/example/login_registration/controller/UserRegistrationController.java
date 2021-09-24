package com.example.login_registration.controller;

import com.example.login_registration.service.UserService;
import com.example.login_registration.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService service;

    public UserRegistrationController(UserService service) {
        this.service = service;
    }

//    @GetMapping
//    public String showForm(Model model){
//        model.addAttribute("user",new UserRegistrationDTO());
//        return "registration";
//    }

//  thymeleaf gets 'user' object from here
    @ModelAttribute("user")
    public UserRegistrationDTO dto(){
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO dto) {
        service.save(dto);
        return "redirect:/registration?success";
    }

}
