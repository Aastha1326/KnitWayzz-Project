package com.example.KnitWayzz.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table(name="postride")
@Entity
public class PostRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @NotBlank(message = "Vehicle details are required")
    private String vehicle;

    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Date is required")
    private String date;

    @Min(value = 1, message = "Minimum 1 passenger required")
    @Max(value = 6, message = "Maximum 6 passengers allowed")
    private int passengers = 1;

    @NotBlank(message = "Start time is required")
    private String timeFrom;


    @NotBlank(message = "End time is required")
    private String timeTo;
    private String notes;

    @Positive(message = "Contribution amount must be positive")
    private Integer maxContribution;

    // Constructors
    public PostRide() {}


    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
    public String getTimeFrom() {
        return timeFrom;
    }
    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }
    public String getTimeTo() {
        return timeTo;
    }
    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Integer getMaxContribution() {
        return maxContribution;
    }
    public void setMaxContribution(Integer maxContribution) {
        this.maxContribution = maxContribution;
    }
}
