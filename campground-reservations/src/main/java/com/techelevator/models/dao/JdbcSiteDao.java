package com.techelevator.models.dao;

import com.techelevator.models.dto.Site;

import com.techelevator.models.dto.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao{
    JdbcTemplate jdbcTemplate;
    private int siteID;
    private int campgroundID;
    private int siteNumber;
    private int maxOccupancy;
    private boolean accessible;
    private int maxRVLength;
    private boolean utilities;

    //Constructor
    public JdbcSiteDao(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public Site getSite(int id) {
        String sql = "SELECT *\n" +
                "FROM site\n" +
                "WHERE site_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

        while(rows.next()) {
            retrieveValues(rows);
            return mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities);
        }
        return null;
    }

    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM site;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(new Site(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }

    @Override
    public List<Site> searchSiteByNumber(int number) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM site\n" +
                "WHERE site_number = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, number);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }

    @Override
    public List<Site> searchSiteByMaxOccupancy(int number, boolean over, boolean under) {
        List<Site> sites = new ArrayList<>();
        String sql = "";
        if (over) {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE max_occupancy < ?;";
        } else if (under) {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE max_occupancy > ?;";
        } else {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE max_occupancy = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, number);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }

    @Override
    public List<Site> searchSiteByHandicap(boolean isHandicapped) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM site\n" +
                "WHERE accessible = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, isHandicapped);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }

    @Override
    public List<Site> searchSiteByRvLength(int number, boolean over, boolean under) {
        List<Site> sites = new ArrayList<>();
        String sql = "";
        if (over) {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE site.max_rv_length < ?;";
        } else if (under) {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE site.max_rv_length > ?;";
        } else {
            sql = "SELECT *\n" +
                    "FROM site\n" +
                    "WHERE max_rv_length = ?;";
        }

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, number);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }

    @Override
    public List<Site> searchSiteByUtilities(boolean utilities) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM site\n" +
                "WHERE utilities = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, utilities);

        while(rows.next()) {
            retrieveValues(rows);
            sites.add(mapToSite(siteID,campgroundID,siteNumber,maxOccupancy,accessible,maxRVLength,utilities));
        }
        return sites;
    }
    private void retrieveValues(SqlRowSet row) {
        this.siteID = row.getInt("site_id");
        this.campgroundID = row.getInt("campground_id");
        this.siteNumber = row.getInt("site_number");
        this.maxOccupancy = row.getInt("max_occupancy");
        this.accessible = row.getBoolean("accessible");
        this.maxRVLength = row.getInt("max_rv_length");
        this.utilities = row.getBoolean("utilities");
    }
    private Site mapToSite(int siteID, int campgroundID, int siteNumber, int maxOccupancy,
                                    boolean accessible, int maxRVLength, boolean utilities) {
        Site site = new Site();
        site.setSiteID(siteID);
        site.setCampgroundID(campgroundID);
        site.setMaxOccupancy(maxOccupancy);
        site.setAccessible(accessible);
        site.setMaxRVLength(maxRVLength);
        site.setUtilities(utilities);
        return site;
    }
}
