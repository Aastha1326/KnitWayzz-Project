package com.example.KnitWayzz.Repository;

import com.example.KnitWayzz.Entity.FindRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FindRideRepository extends JpaRepository<FindRide, Long> {

    List<FindRide> findByPostedBy(String postedBy);

    // Get all rides joined by a user (works for comma-separated list)
    @Query("SELECT f FROM FindRide f WHERE f.joinedBy LIKE %:username%")
    List<FindRide> findByJoinedByContaining(@Param("username") String username);

    List<FindRide> findByPostedByAndStatus(String postedBy, String status);

    // Joined rides by status
    @Query("SELECT f FROM FindRide f WHERE f.status = :status AND f.joinedBy LIKE %:username%")
    List<FindRide> findByJoinedByContainingAndStatus(@Param("username") String username,
                                                     @Param("status") String status);

    // Posted rides excluding joined by user
    @Query("SELECT f FROM FindRide f WHERE f.postedBy = :username AND (f.joinedBy IS NULL OR f.joinedBy NOT LIKE %:username%)")
    List<FindRide> findPostedUnjoined(@Param("username") String username);

    @Query("SELECT f FROM FindRide f WHERE f.joinedBy IS NULL")
    List<FindRide> findAllUnjoined();

    // Fetch all rides except the user's own and rides already joined by the user
    @Query("SELECT f FROM FindRide f WHERE f.postedBy <> :username AND (f.joinedBy IS NULL OR f.joinedBy NOT LIKE %:username%)")
    List<FindRide> findAllExcludingMyRidesAndJoined(@Param("username") String username);

}
