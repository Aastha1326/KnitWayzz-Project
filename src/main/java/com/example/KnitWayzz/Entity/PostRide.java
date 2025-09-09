package com.example.KnitWayzz.Entity;

import jakarta.persistence.*;

@Table(name="postride")
@Entity
public class PostRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    private String vehicle;

    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


    private String source;
    private String destination;
    private String date;
    private int passengers = 1;
    private String timeFrom;
    private String timeTo;
    private String notes;
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
