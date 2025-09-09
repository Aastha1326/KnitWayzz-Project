package com.example.KnitWayzz.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.KnitWayzz.Service.CustomUserDetails;
import com.example.KnitWayzz.Entity.RegisterEntity;
import com.example.KnitWayzz.Repository.RegisterRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RegisterRepository registerRepository;

    @GetMapping("/")
    public String Home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String username;

        if (userDetails != null) {
            RegisterEntity registerEntity = registerRepository.findByEmail(userDetails.getUsername());
            if (registerEntity != null && registerEntity.getRegName() != null) {
                username = registerEntity.getRegName();
            } else {
                username = userDetails.getUsername();
            }
            model.addAttribute("userName", username);
        } else {
            model.addAttribute("userName", "Guest");
        }

        return "Home";
    }

    @GetMapping("/faq")
    public String Faq() {
        return "faq";
    }

    @GetMapping("/support")
    public String support() {
        return "support";
    }

    @GetMapping("/cookies")
    public String cookies() {
        return "cookies";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/other_info")
    public String other() {
        return "other_info";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }

    @GetMapping("/message")
    public String message() {
        return "message";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}
