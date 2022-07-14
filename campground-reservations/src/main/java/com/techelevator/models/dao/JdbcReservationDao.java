package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao{
    JdbcTemplate jdbcTemplate;
    private int reservationID;
    private int siteID;
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate createDate;

    //Constructor
    public JdbcReservationDao(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}
    @Override
    public Reservation getReservation(int id) {
        String sql = "SELECT *\n" +
                "FROM reservation\n" +
                "WHERE reservation_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql,id);

        if(row.next()) {
            retrieveValues(row);
            return mapToReservation(reservationID,siteID,name,fromDate,toDate,createDate);
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM reservation;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while (rows.next()) {
            retrieveValues(rows);
            reservations.add(new Reservation(reservationID,siteID,name,fromDate,toDate,createDate));
        }
        return reservations;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation(SITE_ID, NAME, FROM_DATE, TO_DATE, CREATE_DATE) \n" +
                "VALUES \n" +
                "(\n" +
                "    ?,?,?,?,?\n" +
                ") RETURNING reservation_id;";

        reservation.setSiteID(jdbcTemplate.update(sql, reservation.getSiteID(),reservation.getName()
                                        ,reservation.getFromDate(), reservation.getToDate()
                                                ,reservation.getCreateDate())
        );


        return reservation;
    }

    @Override
    public void removeReservation(int id) {
        String sql = "DELETE \n" +
                "FROM reservation\n" +
                "WHERE reservation_id = ?;";
        int number = jdbcTemplate.update(sql, id);
    }

    @Override
    public Reservation modifyReservationSite(Reservation reservation, int site) {
        String sql = "UPDATE reservation\n" +
                "SET site_id = ?\n" +
                "WHERE reservation_id = ?\n" +
                "RETURNING site_id;";

        reservation.setSiteID(jdbcTemplate.update(sql, site, reservation.getReservationID()));

        return reservation;
    }

    @Override
    public Reservation modifyReservationName(Reservation reservation, String name) {
        String sql = "UPDATE reservation\n" +
                "SET name = ?\n" +
                "WHERE reservation_id = ?\n" +
                "RETURNING site_id;";

        reservation.setSiteID(jdbcTemplate.update(sql, name, reservation.getReservationID()));

        return reservation;
    }

    @Override
    public Reservation modifyReservationFromDate(Reservation reservation, LocalDate date) {
        String sql = "UPDATE reservation\n" +
                "SET from_date = ?\n" +
                "WHERE reservation_id = ?\n" +
                "RETURNING site_id;";

        reservation.setSiteID(jdbcTemplate.update(sql, date, reservation.getReservationID()));

        return reservation;
    }

    @Override
    public Reservation modifyReservationToDate(Reservation reservation, LocalDate date) {
        String sql = "UPDATE reservation\n" +
                "SET to_date = ?\n" +
                "WHERE reservation_id = ?\n" +
                "RETURNING site_id;";

        reservation.setSiteID(jdbcTemplate.update(sql, date, reservation.getReservationID()));

        return reservation;
    }


    @Override
    public List<Reservation> searchReservationByName(String name) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM reservation\n" +
                "WHERE name = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, name);

        while (rows.next()) {
            retrieveValues(rows);
            reservations.add(new Reservation(reservationID,siteID,this.name,fromDate,toDate,createDate));
        }
        return reservations;
    }

    @Override
    public List<Reservation> searchReservationByDate(LocalDate date, boolean before, boolean after) {
        return null;
    }
    private void retrieveValues(SqlRowSet rows) {
        this.reservationID = rows.getInt("reservation_id");
        this.siteID = rows.getInt("site_id");
        this.name = rows.getString("name");
        this.fromDate = rows.getDate("from_date").toLocalDate();
        this.toDate = rows.getDate("to_date").toLocalDate();
        this.createDate = rows.getDate("create_date").toLocalDate();
    }
    private Reservation mapToReservation(int reservationID, int siteID, String name, LocalDate fromDate
                                                        ,LocalDate toDate, LocalDate createDate) {
        Reservation reservation = new Reservation();
        reservation.setReservationID(reservationID);
        reservation.setSiteID(siteID);
        reservation.setName(name);
        reservation.setFromDate(fromDate);
        reservation.setToDate(toDate);
        reservation.setCreateDate(createDate);

        return reservation;
    }

}
