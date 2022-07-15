package com.techelevator.models.dao;
import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Campground;
import junit.framework.TestCase;
import com.techelevator.models.dto.Park;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcCampgroundDaoTest extends TestCase {


    public JdbcCampgroundDao setup() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        return new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void testGetCampground() {
        //Expected
        Campground campground = new Campground(3,1,"Schoodic Woods", 05,  10,30);

        String message = "should return row where id match";

        assertEquals(message, campground.getCampgroundID(), setup().getCampground(3).getCampgroundID());
    }

    public void testGetCampgroundByParkID() {
        int parkID = 1;
        int numberOfMatches = 3;

        String message = "should return row where park id match";
        List<Campground> campgrounds = setup().getCampgroundByParkID(1);
        assertEquals(message, 3, campgrounds.size());
    }

    public void testGetAllCampgrounds() {
        int parkID = 1;
        int numberOfMatches = 3;

        String message = "should return all rows";
        List<Campground> campgrounds = setup().getAllCampgrounds();
        assertEquals(message, 7, campgrounds.size());
    }

    public void testSearchCampgroundByName() {
        Campground campground = new Campground(3,1,"Schoodic Woods", 05,  10,30);
        String name = "Schoodic Woods";
        String message = "The names differ";
        assertEquals(message, campground.getName(), setup().searchCampgroundByName(name).getName());
    }

    public void testMapToCampground() {
    }

    public void testRetrieveValues() {
    }
}
