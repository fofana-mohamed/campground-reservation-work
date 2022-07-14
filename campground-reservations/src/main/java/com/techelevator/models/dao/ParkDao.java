package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;

import java.time.LocalDate;
import java.util.List;

public interface ParkDao {

    public Park getPark(int id);
    public List<Park> getAllParks();
    public List<Park> searchParkByName(String name);
    public List<Park> searchParkByLocation(String location);
    public List<Park> searchParkByEstDate(LocalDate date, boolean before, boolean under);
    public List<Park> searchParkByArea(int area, boolean over, boolean under);
    public List<Park> searchParkByVisitorCount(int number, boolean over, boolean under);
    public Park searchParkByReservation(int reservationID);
}
