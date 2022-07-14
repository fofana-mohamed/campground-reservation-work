package com.techelevator.controllers;
import com.techelevator.models.dao.JdbcReservationDao;
import com.techelevator.models.dao.JdbcSiteDao;
import com.techelevator.models.dao.JdbcCampgroundDao;
import com.techelevator.models.dao.JdbcParkDao;
import com.techelevator.views.UserInterface;

import javax.sql.DataSource;

public class CampgroundApplication  {

    JdbcParkDao jdbcParkDao;
    public CampgroundApplication(DataSource datasource) {
        this.jdbcParkDao = new JdbcParkDao(datasource);
    }

    public void run() {
        UserInterface userInterface = new UserInterface();
        while(true) {
            String choice = userInterface.viewParksScreen(jdbcParkDao);

            if (choice.equalsIgnoreCase("Q")) {break;}
            else {

            }
        }
    }
}
