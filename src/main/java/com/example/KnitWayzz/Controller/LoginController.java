package com.example.KnitWayzz.Controller;

import com.example.KnitWayzz.Entity.LoginEntity;
import com.example.KnitWayzz.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String login(Model model){
        model.addAttribute("login",new LoginEntity());
        return "login";
    }


}
