package com.example.KnitWayzz.Service;

import com.example.KnitWayzz.Entity.FindRide;
import com.example.KnitWayzz.Repository.FindRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Locale;


import java.util.List;
import java.util.Optional;

@Service
public class FindRideService {

    @Autowired
    private FindRideRepository findRideRepository;

    // Fetch all rides posted by a user
    public List<FindRide> getPostedRides(String username) {
        return findRideRepository.findByPostedBy(username);
    }

    // Fetch all rides joined by a user
    public List<FindRide> getJoinedRides(String username) {
        return findRideRepository.findByJoinedBy(username);
    }

    // Save a new ride (post or join)
    public FindRide saveRide(FindRide ride) {
        return findRideRepository.save(ride);
    }

    // Find ride by ID
    public Optional<FindRide> findById(Long id) {
        return findRideRepository.findById(id);
    }

    // Posted rides of a user filtered by status
    public List<FindRide> getPostedRidesByStatus(String username, String status) {
        return findRideRepository.findByPostedByAndStatus(username, status);
    }

    // Joined rides of a user filtered by status
    public List<FindRide> getJoinedRidesByStatus(String username, String status) {
        return findRideRepository.findByJoinedByAndStatus(username, status);
    }

    // Delete a ride
    public void deleteRide(Long id) {
        findRideRepository.deleteById(id);
    }

    // Get ride by ID
    public FindRide getRideById(Long id) {
        return findRideRepository.findById(id).orElse(null);
    }

    // ✅ All available rides (anyone’s rides that are not yet joined)
    public List<FindRide> getAllUnjoinedRides() {
        return findRideRepository.findAllUnjoined();
    }
    public List<FindRide> getAllUnjoinedRidesExceptMine(String username) {
        return findRideRepository.findAllUnjoinedExceptMine(username);
    }


    // ✅ Only current user’s rides that are still unjoined
    public List<FindRide> getUnjoinedPostedRides(String username) {
        return findRideRepository.findPostedUnjoined(username);
    }

    // Cancel joined ride
    public void cancelJoinedRide(Long rideId, String username) {
        FindRide ride = getRideById(rideId);
        if (ride != null && username.equals(ride.getJoinedBy())) {
            ride.setJoinedBy(null);
            ride.setStatus("posted");
            saveRide(ride);
        }
    }

    // Search + prioritize logic
    public List<FindRide> searchAndPrioritize(List<FindRide> rides, String src, String dest) {
        if ((src == null || src.isBlank()) && (dest == null || dest.isBlank())) {
            return rides;
        }

        final String s = src == null ? "" : src.trim().toLowerCase(Locale.ROOT);
        final String d = dest == null ? "" : dest.trim().toLowerCase(Locale.ROOT);

        Comparator<FindRide> cmp = Comparator
                .comparingInt((FindRide r) -> {
                    String rs = r.getSource() == null ? "" : r.getSource().toLowerCase(Locale.ROOT);
                    String rd = r.getDestination() == null ? "" : r.getDestination().toLowerCase(Locale.ROOT);

                    boolean srcExact = !s.isEmpty() && rs.equals(s);
                    boolean destExact = !d.isEmpty() && rd.equals(d);
                    boolean srcPart = !s.isEmpty() && rs.contains(s);
                    boolean destPart = !d.isEmpty() && rd.contains(d);

                    // Higher score = higher priority
                    int score = 0;
                    if (srcExact) score += 3;
                    if (destExact) score += 3;
                    if (srcPart && !srcExact) score += 1;
                    if (destPart && !destExact) score += 1;

                    // Negate because we want higher scores first
                    return -score;
                })
                // optional: break ties by date/time if you want
                .thenComparing(FindRide::getDate, Comparator.nullsLast(Comparator.naturalOrder()));

        return rides.stream().sorted(cmp).toList();
    }

}
