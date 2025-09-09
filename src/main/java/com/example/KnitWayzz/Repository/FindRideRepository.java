package com.example.KnitWayzz.Repository;

import com.example.KnitWayzz.Entity.FindRide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FindRideRepository extends JpaRepository<FindRide,Long> {
    List<FindRide> findByPostedBy(String postedBy);

    // Get all rides joined by a user
    List<FindRide> findByJoinedBy(String joinedBy);

    // Optionally filter by status
    List<FindRide> findByPostedByAndStatus(String postedBy, String status);
    List<FindRide> findByJoinedByAndStatus(String joinedBy, String status);

    List<FindRide> findByPostedByAndJoinedByNot(String postedBy, String joinedBy);
    // If joinedBy can be null, use a custom query:
    @Query("SELECT f FROM FindRide f WHERE f.postedBy = :username AND (f.joinedBy IS NULL OR f.joinedBy <> :username)")
    List<FindRide> findPostedUnjoined(@Param("username") String username);

    @Query("SELECT f FROM FindRide f WHERE f.joinedBy IS NULL")
    List<FindRide> findAllUnjoined();

    @Query("SELECT f FROM FindRide f WHERE f.joinedBy IS NULL AND f.postedBy <> :username")
    List<FindRide> findAllUnjoinedExceptMine(@Param("username") String username);

}
