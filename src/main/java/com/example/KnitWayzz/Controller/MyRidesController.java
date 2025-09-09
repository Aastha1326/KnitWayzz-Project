package com.example.KnitWayzz.Controller;

import com.example.KnitWayzz.Entity.FindRide;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.KnitWayzz.Service.FindRideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MyRidesController {

    @Autowired
    private FindRideService findRideService;

    @GetMapping("/my_rides")
    public String showMyJoinedRides(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();

        List<FindRide> joinedRides = findRideService.getJoinedRides(username);
        List<FindRide> postedRides = findRideService.getPostedRides(username);

        model.addAttribute("postedRides", postedRides);
        model.addAttribute("joinedRides", joinedRides);

        return "my_rides";
    }

    @PostMapping("/myrides_cancel/{id}")
    public String myCancelJoinedRide(@PathVariable Long id, Principal principal) {
        findRideService.cancelJoinedRide(id, principal.getName()); // Implement logic in service
        return "redirect:/my_rides";
    }

}
