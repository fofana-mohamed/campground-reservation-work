package com.techelevator.models.dto;

public class Site {
    // Instance variables
    private int siteID;
    private int campgroundID;
    private int siteNumber;
    private int maxOccupancy;
    private boolean accessible;
    private int maxRVLength;
    private boolean utilities;

    //Constructors
    public Site() {}

    public Site(int siteID, int campgroundID, int siteNumber, int maxOccupancy, boolean accessible, int maxRVLength, boolean utilities) {
        this.siteID = siteID;
        this.campgroundID = campgroundID;
        this.siteNumber = siteNumber;
        this.maxOccupancy = maxOccupancy;
        this.accessible = accessible;
        this.maxRVLength = maxRVLength;
        this.utilities = utilities;
    }

    //Getters and Setters
    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public int getCampgroundID() {
        return campgroundID;
    }

    public void setCampgroundID(int campgroundID) {
        this.campgroundID = campgroundID;
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public int getMaxRVLength() {
        return maxRVLength;
    }

    public void setMaxRVLength(int maxRVLength) {
        this.maxRVLength = maxRVLength;
    }

    public boolean isUtilities() {
        return utilities;
    }

    public void setUtilities(boolean utilities) {
        this.utilities = utilities;
    }





}
