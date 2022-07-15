package com.techelevator.models.dao;
import com.techelevator.models.dao.*;

import com.techelevator.models.dto.Site;
import junit.framework.TestCase;
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
public class JdbcSiteDaoTest extends DAOIntegrationTest {
    JdbcSiteDao jdbcSiteDao;
    @Before
    public void setup() {
        jdbcSiteDao = new JdbcSiteDao(getDataSource());
    }
    @Test
    public void testGetSite() {
        int site = 10;
        assertEquals(site, jdbcSiteDao.getSite(site).getSiteID());
    }

    @Test
    public void testGetAllSites() {
        int numberOfSites = 622;
        assertEquals(numberOfSites, jdbcSiteDao.getAllSites().size());
    }

    @Test
    public void testGetAllSitesByCampground() {
        int numberOfRows = 276;
        int campgroundID = 1;
        assertEquals(numberOfRows, jdbcSiteDao.getAllSitesByCampground(campgroundID).size());
    }

    @Test
    public void testGetAvailableSitesByCampground() {
        LocalDate fromDate = LocalDate.of(2022,07,12);
        LocalDate toDate = LocalDate.of(2022,07,16);
        int campgroundID = 1;

        Site site = new Site(4,1,4,6,false,0,false);

        assertEquals(site.getSiteID(),jdbcSiteDao.getAvailableSitesByCampground(campgroundID,fromDate,toDate).get(0).getSiteID());

    }
}
