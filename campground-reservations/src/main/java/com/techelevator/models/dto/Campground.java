package com.techelevator.models.dto;

import java.math.BigDecimal;

public class Campground {
    // Instance variables
    private int campgroundID;
    private int parkID;
    private String name;
    private int openFrom;
    private int openTo;
    private double dailyFee;

    //Constructors
    public Campground() {}

    public Campground (int campgroundID, int parkID, String name, int openFrom, int openTo, int dailyFee) {
        this.campgroundID = campgroundID;
        this.parkID = parkID;
        this.name = name;
        this.openFrom = openFrom;
        this.openTo = openTo;
        this.dailyFee = dailyFee;
    }

    //Getters and Setters
    public int getCampgroundID() {
        return campgroundID;
    }

    public void setCampgroundID(int campgroundID) {
        this.campgroundID = campgroundID;
    }

    public int getParkID() {
        return parkID;
    }

    public void setParkID(int parkID) {
        this.parkID = parkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(int openFrom) {
        this.openFrom = openFrom;
    }

    public int getOpenTo() {
        return openTo;
    }

    public void setOpenTo(int openTo) {
        this.openTo = openTo;
    }

    public double getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(double dailyFee) {
        this.dailyFee = dailyFee;
    }




}
