package com.techelevator.models.dto;

import java.time.LocalDate;

public class Reservation {

    private int reservationID;
    private int siteID;
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate createDate;

    //Constructors
    public Reservation() {}

    public Reservation(int reservationID, int siteID, String name, LocalDate fromDate, LocalDate toDate, LocalDate createDate) {
        this.reservationID = reservationID;
        this.siteID = siteID;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createDate = createDate;
    }

    //Getters and Setters
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
