package com.example.KnitWayzz.Controller;

import com.example.KnitWayzz.Entity.FindRide;
import com.example.KnitWayzz.Entity.PostRide;
import com.example.KnitWayzz.Service.FindRideService;
import com.example.KnitWayzz.Service.PostRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;

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
    public String postRideAndRedirectToMyRides(@Valid @ModelAttribute("rideSearch") PostRide ride,
                                               BindingResult result,
                                               Principal principal,
                                               Model model) {

        // Attach username
        ride.setUsername(principal!= null ? principal.getName(): "Guest");

        // Validate date (prevent past dates)
        if (ride.getDate().isBefore(LocalDate.now())) {
            result.rejectValue("date", "error.rideSearch", "Date cannot be in the past");
        }

        if (result.hasErrors()) {
            return "post_ride";
        }

        // Save PostRide
        PostRide savedRide = postRideService.saveRide(ride);

        // Map PostRide → FindRide
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
        findRide.setPostedBy(principal.getName());
        findRide.setStatus("posted");

        findRideService.saveRide(findRide);

        return "redirect:/find_ride";
    }
}
