package com.example.KnitWayzz.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="findride")
public class FindRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private String date;          // Format: yyyy-MM-dd (can switch to LocalDate if needed)
    private int passengers = 1;
    private String timeFrom;
    private String timeTo;
    private String notes;
    private Integer maxContribution;

    private String mobileNo;

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    private Integer seatsAvailable;

    private String vehicle;

    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }
    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    private String postedBy;      // Username/email of poster
    private String joinedBy;      // Username/email of joiner
    private String status;        // "posted", "joined", "completed", etc.

    public FindRide() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }

    public String getTimeFrom() { return timeFrom; }
    public void setTimeFrom(String timeFrom) { this.timeFrom = timeFrom; }

    public String getTimeTo() { return timeTo; }
    public void setTimeTo(String timeTo) { this.timeTo = timeTo; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Integer getMaxContribution() { return maxContribution; }
    public void setMaxContribution(Integer maxContribution) { this.maxContribution = maxContribution; }

    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }

    public String getJoinedBy() { return joinedBy; }
    public void setJoinedBy(String joinedBy) { this.joinedBy = joinedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

