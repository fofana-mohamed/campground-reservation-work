package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    public Reservation getReservation(int id);
    public List<Reservation> getAllReservations();
    public Reservation addReservation(Reservation reservation);
    public void removeReservation(int id);
    public Reservation modifyReservationSite (Reservation reservation, int site);
    public Reservation modifyReservationName (Reservation reservation, String name);
    public Reservation modifyReservationFromDate (Reservation reservation, LocalDate date);
    public Reservation modifyReservationToDate (Reservation reservation, LocalDate date);
    public List<Reservation> searchReservationByName(String name);
    public List<Reservation> searchReservationByDate(LocalDate date, boolean before, boolean after);
}
