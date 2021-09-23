package com.example.login_registration.web;

import com.example.login_registration.service.UserService;
import com.example.login_registration.web.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
