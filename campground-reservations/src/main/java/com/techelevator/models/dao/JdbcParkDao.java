package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {
    JdbcTemplate jdbcTemplate;
    private int parkId;
    private String name;
    private String location;
    private LocalDate date;
    private int area;
    private int visitors;
    private String description;

    public JdbcParkDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Park> getAllParks() {

        List<Park> parks = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM park;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,name,location,date,area,visitors,description));
        }
        return parks;
    }

    @Override
    public List<Park> searchParkByName(String name) {
        List<Park> parks = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM park " +
                "WHERE name = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, name);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,this.name,location,date,area,visitors,description));
        }
        return parks;
    }

    @Override
    public List<Park> searchParkByLocation(String location) {
        List<Park> parks = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM park " +
                "WHERE location = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, location);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,name,this.location,date,area,visitors,description));
        }
        return parks;
    }

    @Override
    public List<Park> searchParkByEstDate(LocalDate date, boolean before, boolean after) {
        List<Park> parks = new ArrayList<>();
        String sql = "";

        if (before) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE establish_date > ?;";
        } else if (after) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE establish_date < ?;";
        } else {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE establish_date = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, date);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,name,location,this.date,area,visitors,description));
        }
        return parks;
    }

    @Override
    public List<Park> searchParkByArea(int area, boolean over, boolean under) {
        List<Park> parks = new ArrayList<>();
        String sql = "";

        if (under) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE area > ?;";
        } else if (over) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE area < ?;";
        } else {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE area = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, area);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,name,location,date,this.area,visitors,description));
        }
        return parks;
    }

    @Override
    public List<Park> searchParkByVisitorCount(int number, boolean over, boolean under) {
        List<Park> parks = new ArrayList<>();
        String sql = "";

        if (under) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE park.visitors > ?;";
        } else if (over) {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE park.visitors < ?;";
        } else {
            sql = "SELECT * " +
                    "FROM park " +
                    "WHERE visitors = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, number);

        while(rows.next()) {
            retrieveValues(rows);
            parks.add(mapToParks(parkId,name,location,date,area,visitors,description));
        }
        return parks;
    }

    @Override
    public Park searchParkByReservation(int reservationID) {

        return null;
    }

    @Override
    public Park getPark(int parkId) {
        Park park = new Park();

        String sql = "SELECT * " +
                "FROM park " +
                "WHERE park_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
        retrieveValues(rows);
        park = mapToParks(parkId,name,location,date,area,visitors,description);

        return park;
    }

    private Park mapToParks(int parkID, String name, String location, LocalDate establishDate,
                            int area, int visitors, String description) {
        Park park = new Park();
        park.setParkId(parkID);
        park.setName(name);
        park.setLocation(location);
        park.setDate(establishDate);
        park.setArea(area);
        park.setVisitors(visitors);
        park.setDescription(description);

        return park;
    }
    public void retrieveValues(SqlRowSet row) {
        parkId = row.getInt("park_id");
        name = row.getString("name");
        location = row.getString("location");
        date = row.getDate("establish_date").toLocalDate();
        area = row.getInt("area");
        visitors = row.getInt("visitors");
        description = row.getString("description");
    }
}
