package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class JdbcCampgroundDao implements CampgroundDao{
    JdbcTemplate jdbcTemplate;
    private int campgroundID;
    private int parkID;
    private String name;
    private int openFrom;
    private int openTo;
    private double dailyFee;

    //Constructor
    public JdbcCampgroundDao(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}
    @Override
    public Campground getCampground(int id) {
        Campground campground = new Campground();

        String sql = "SELECT * " +
                "FROM campground " +
                "WHERE campground_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

        while(rows.next()) {
            retrieveValues(rows);
            campground = mapToCampground(campgroundID, parkID, name, openFrom, openTo, dailyFee);
        }
        return campground;
    }

    @Override
    public List<Campground> getCampgroundByParkID(int id) {
        List<Campground> campgrounds = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM campground " +
                "WHERE park_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

        while(rows.next()) {
            retrieveValues(rows);
            campgrounds.add(mapToCampground(campgroundID, parkID, name, openFrom, openTo, dailyFee));
        }
        return campgrounds;
    }

    @Override
    public List<Campground> getAllCampgrounds() {
        List<Campground> campgrounds = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM campground;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next()) {
            retrieveValues(rows);
            campgrounds.add(mapToCampground(campgroundID, parkID, name, openFrom, openTo, dailyFee));
        }
        return campgrounds;
    }

    @Override
    public Campground searchCampgroundByName(String name) {
        String sql = "SELECT * " +
                "FROM campground " +
                "WHERE name = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, name);

        while(rows.next()) {
            retrieveValues(rows);
           return mapToCampground(campgroundID, parkID, this.name, openFrom, openTo, dailyFee);
        }
        return null;
    }

    public Campground mapToCampground(int campgroundID, int parkID, String name, int openFrom, int openTo, double dailyFee) {
        Campground campground = new Campground();
        campground.setCampgroundID(campgroundID);
        campground.setParkID(parkID);
        campground.setName(name);
        campground.setOpenFrom(openFrom);
        campground.setOpenTo(openTo);
        campground.setDailyFee(dailyFee);

        return campground;
    }
    public void retrieveValues(SqlRowSet rows) {
        this.campgroundID = rows.getInt("campground_id");
        this.parkID = rows.getInt("park_id");
        this.name = rows.getString("name");
        this.openFrom = rows.getInt("open_from_mm");
        this.openTo = rows.getInt("open_to_mm");
        this.dailyFee = rows.getDouble("daily_fee");
    }
}
