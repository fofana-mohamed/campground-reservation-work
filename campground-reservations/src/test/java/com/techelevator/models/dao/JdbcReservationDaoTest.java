package com.techelevator.models.dao;
import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Reservation;
import junit.framework.TestCase;
import com.techelevator.models.dto.Park;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

public class JdbcReservationDaoTest extends DAOIntegrationTest {
    JdbcReservationDao jdbcReservationDao;
    @Before
    public void setup() {jdbcReservationDao = new JdbcReservationDao(getDataSource());}

    @Test
    public void testGetReservation() {
        int reservationID = 18;
        assertEquals(reservationID, jdbcReservationDao.getReservation(reservationID).getReservationID());
    }

    @Test
    public void testGetAllReservations() {
        int numberOfRows = 44;
        assertEquals(numberOfRows, jdbcReservationDao.getAllReservations().size());
    }

    @Test
    public void testAddReservation() {
        int id = 45;
        LocalDate fromDate = LocalDate.of(2022,07,12);
        LocalDate toDate = LocalDate.of(2022,07,16);
        LocalDate createDate = LocalDate.now();
        Reservation reservation = new Reservation(0,4,"Mohamed",fromDate,toDate, createDate);

        assertEquals(id, jdbcReservationDao.addReservation(reservation).getReservationID());
    }
}
