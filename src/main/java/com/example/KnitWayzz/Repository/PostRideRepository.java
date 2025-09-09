package com.example.KnitWayzz.Repository;


import com.example.KnitWayzz.Entity.PostRide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRideRepository extends JpaRepository<PostRide,Long> {
    List<PostRide> findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDate(
            String source, String destination, String date
    );
}
