package com.example.KnitWayzz.Controller;

import com.example.KnitWayzz.Entity.FindRide;
import com.example.KnitWayzz.Service.FindRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class FindRideController {

    @Autowired
    private FindRideService findRideService;

    @PostMapping("/accept_ride/{id}")
    public String acceptRide(@PathVariable Long id, Principal principal) {
        FindRide ride = findRideService.getRideById(id);

        if (ride != null && (ride.getJoinedBy() == null || ride.getJoinedBy().isEmpty())) {
            ride.setJoinedBy(principal.getName()); // Set the current user as joiner
            ride.setStatus("joined"); // Optional: update ride status
            findRideService.saveRide(ride);
        }
        return "redirect:/find_ride";
    }


    // Display the My Rides page for the current user
    @GetMapping("/find_ride")
    public String showMyRides(Model model, Principal principal,
                              @RequestParam(name = "source", required = false) String source,
                              @RequestParam(name = "destination", required = false) String destination) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();


        List<FindRide> postedRides = findRideService.getAllUnjoinedRidesExceptMine(username);

        postedRides = findRideService.searchAndPrioritize(postedRides, source, destination);
        model.addAttribute("postedRides", postedRides);
        // Only unjoined posted rides
        List<FindRide> joinedRides = findRideService.getJoinedRides(username);         // All joined rides

        model.addAttribute("postedRides", postedRides);
        model.addAttribute("joinedRides", joinedRides);

        postedRides = findRideService.searchAndPrioritize(postedRides, source, destination);

        model.addAttribute("postedRides", postedRides);
        model.addAttribute("joinedRides", joinedRides);

        // Preserve search values to keep inputs sticky
        model.addAttribute("sourceQuery", source);
        model.addAttribute("destinationQuery", destination);

        return "find_ride";
    }

    @PostMapping("/cancel_ride/{id}")
    public String cancelJoinedRide(@PathVariable Long id, Principal principal) {
        findRideService.cancelJoinedRide(id, principal.getName()); // Implement logic in service
        return "redirect:/find_ride";
    }


}
