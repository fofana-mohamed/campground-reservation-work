package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;

import java.math.BigDecimal;
import java.util.List;

public interface CampgroundDao {

    public Campground getCampground(int id);
    public List<Campground> getAllCampgrounds();
    public List<Campground> searchCampgroundByName(String name);
    public List<Campground> searchCampgroundsByCost(BigDecimal cost, boolean over, boolean under);
    public List<Campground> searchCampgroundsBySeason(int openFrom, int openTo);
}
