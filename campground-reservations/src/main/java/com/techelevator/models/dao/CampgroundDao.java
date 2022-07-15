package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;

import java.math.BigDecimal;
import java.util.List;

public interface CampgroundDao {

    public Campground getCampground(int id);
    public List<Campground> getCampgroundByParkID(int id);
    public List<Campground> getAllCampgrounds();
    public Campground searchCampgroundByName(String name);
}
