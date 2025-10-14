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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FindRideController {

    @Autowired
    private FindRideService findRideService;

    @PostMapping("/accept_ride/{id}")
    public String acceptRide(@PathVariable Long id, Principal principal) {
        try {
            FindRide ride = findRideService.getRideById(id);
            if (ride == null) return "redirect:/find_ride?error=true";

            String currentUser = principal.getName();

            // Initialize seatsAvailable if null
            if (ride.getSeatsAvailable() == null) {
                ride.setSeatsAvailable(ride.getPassengers());
            }

            // Create a List of joined users safely
            List<String> joinedList = new ArrayList<>();
            if (ride.getJoinedBy() != null && !ride.getJoinedBy().isEmpty()) {
                for (String user : ride.getJoinedBy().split(",")) {
                    if (!user.isBlank()) joinedList.add(user.trim());
                }
            }

            // If user already joined, do nothing
            if (joinedList.contains(currentUser)) {
                return "redirect:/find_ride";
            }

            // Check if seats are available
            if (joinedList.size() >= ride.getPassengers()) {
                // All seats full
                return "redirect:/find_ride?error=true";
            }

            // Add user to joined list
            joinedList.add(currentUser);
            ride.setJoinedBy(String.join(",", joinedList));

            // Update seatsAvailable
            ride.setSeatsAvailable(ride.getPassengers() - joinedList.size());

            // Save ride
            findRideService.saveRide(ride);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/find_ride?error=true";
        }

        return "redirect:/find_ride";
    }


    @PostMapping("/cancel_ride/{id}")
    public String cancelJoinedRide(@PathVariable Long id, Principal principal) {
        try {
            FindRide ride = findRideService.getRideById(id);
            if (ride == null || ride.getJoinedBy() == null) return "redirect:/find_ride?error=true";

            String username = principal.getName();
            String joined = ride.getJoinedBy().replace(username, "").replace(",,", ",");
            ride.setJoinedBy(joined.isEmpty() ? null : joined);

            Integer seats = ride.getSeatsAvailable();
            ride.setSeatsAvailable(seats == null ? 1 : seats + 1);

            findRideService.saveRide(ride);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/find_ride?error=true";
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

        // Get all rides except user's own rides and already joined rides
        List<FindRide> postedRides = findRideService.getAllRidesExcludingMine(username);

        // Optional: search by source/destination
        postedRides = findRideService.searchAndPrioritize(postedRides, source, destination);

        // Get rides already joined by the user
        List<FindRide> joinedRides = findRideService.getJoinedRides(username);

        model.addAttribute("postedRides", postedRides);
        model.addAttribute("joinedRides", joinedRides);

        model.addAttribute("sourceQuery", source);
        model.addAttribute("destinationQuery", destination);

        return "find_ride";
    }

}
