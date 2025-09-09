package com.example.KnitWayzz.Service;


import com.example.KnitWayzz.Entity.PostRide;
import com.example.KnitWayzz.Repository.PostRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostRideService {

    @Autowired
    private PostRideRepository postRideRepository;
    public PostRide saveRide(PostRide ride) {
        return postRideRepository.save(ride);
    }

    // Search rides by filters (basic example)
    public List<PostRide> searchRides(String source, String destination, String date) {
        return postRideRepository.findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDate(
                source, destination, date);
    }

    // Get all rides
    public List<PostRide> getAllRides() {
        return postRideRepository.findAll();
    }

    // Find by ID
    public PostRide getRideById(Long id) {
        return postRideRepository.findById(id).orElse(null);
    }
}
