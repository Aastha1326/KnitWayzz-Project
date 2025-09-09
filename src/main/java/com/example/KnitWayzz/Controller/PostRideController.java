package com.example.KnitWayzz.Controller;

import com.example.KnitWayzz.Entity.FindRide;
import com.example.KnitWayzz.Entity.PostRide;
import com.example.KnitWayzz.Service.PostRideService;
import com.example.KnitWayzz.Service.FindRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class PostRideController {

    @Autowired
    private PostRideService postRideService;

    @Autowired
    private FindRideService findRideService;

    // Show Post Ride form
    @GetMapping("/post_ride")
    public String showPostRideForm(Model model) {
        model.addAttribute("rideSearch", new PostRide());
        return "post_ride";
    }

    // Save PostRide → also copy data into FindRide
    @PostMapping("/post_ride")
    public String postRideAndRedirectToMyRides(@ModelAttribute("rideSearch") PostRide ride,
                                               Principal principal) {
        // Attach username from logged-in user
        ride.setUsername(principal.getName());

        // 1. Save in PostRide table
        PostRide savedRide = postRideService.saveRide(ride);

        // 2. Map PostRide → FindRide
        FindRide findRide = new FindRide();
        findRide.setMobileNo(savedRide.getMobileNo());
        findRide.setVehicle(savedRide.getVehicle());
        findRide.setSource(savedRide.getSource());
        findRide.setDestination(savedRide.getDestination());
        findRide.setDate(savedRide.getDate());
        findRide.setTimeFrom(savedRide.getTimeFrom());
        findRide.setTimeTo(savedRide.getTimeTo());
        findRide.setPassengers(savedRide.getPassengers());
        findRide.setMaxContribution(savedRide.getMaxContribution());
        findRide.setNotes(savedRide.getNotes());

        // extra fields
        findRide.setPostedBy(principal.getName());
        findRide.setStatus("posted");

        // 3. Save in FindRide table
        findRideService.saveRide(findRide);

        // Redirect to MyRides or FindRide
        return "redirect:/find_ride";  // or "redirect:/my_rides"

    }
}
