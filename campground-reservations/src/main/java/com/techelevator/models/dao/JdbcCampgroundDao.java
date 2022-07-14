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
    private int dailyFee;

    //Constructor
    public JdbcCampgroundDao(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}
    @Override
    public Campground getCampground(int id) {
        Campground campground = new Campground();

        String sql = "SELECT * " +
                "FROM campground " +
                "WHERE campground_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next()) {
            retrieveValues(rows);
            campground = mapToCampground(campgroundID, parkID, name, openFrom, openTo, dailyFee);
        }
        return campground;
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
    public List<Campground> searchCampgroundByName(String name) {
        List<Campground> campgrounds = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM campground " +
                "WHERE name = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, name);

        while(rows.next()) {
            retrieveValues(rows);
            campgrounds.add(mapToCampground(campgroundID, parkID, this.name, openFrom, openTo, dailyFee));
        }
        return campgrounds;
    }

    @Override
    public List<Campground> searchCampgroundsByCost(BigDecimal cost, boolean over, boolean under) {
        List<Campground> campgrounds = new ArrayList<>();
        String sql = "";
        if (over) {
            sql = "SELECT * " +
                    "FROM campground " +
                    "WHERE daily_fee < ?;";
        } else if (under) {
            sql = "SELECT * " +
                    "FROM campground " +
                    "WHERE daily_fee > ?;";
        } else {
            sql = "SELECT * " +
                    "FROM campground " +
                    "WHERE daily_fee = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, cost);

        while(rows.next()) {
            retrieveValues(rows);
            campgrounds.add(mapToCampground(campgroundID, parkID, this.name, openFrom, openTo, dailyFee));
        }
        return campgrounds;
    }

    @Override
    public List<Campground> searchCampgroundsBySeason(int openFrom, int openTo) {
        return null;
    }
    public Campground mapToCampground(int campgroundID, int parkID, String name, int openFrom, int openTo, int dailyFee) {
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
        this.dailyFee = rows.getInt("daily_fee");
    }
}
