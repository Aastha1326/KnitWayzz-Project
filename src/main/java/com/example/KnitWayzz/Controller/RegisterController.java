package com.example.KnitWayzz.Controller;


import com.example.KnitWayzz.Entity.LoginEntity;
import com.example.KnitWayzz.Entity.RegisterEntity;
import com.example.KnitWayzz.Service.RegisterService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    private String register(Model model){
        model.addAttribute("register",new RegisterEntity());
        return "registration";
    }

    @PostMapping
    public String save(@ModelAttribute("register") RegisterEntity register){
        registerService.save(register);
        return "redirect:/login";
    }

}
