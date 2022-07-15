package com.techelevator.controllers;
import com.techelevator.models.dao.*;
import com.techelevator.views.UserInterface;

import javax.sql.DataSource;
import java.time.LocalDate;

public class CampgroundApplication  {

    JdbcParkDao jdbcParkDao;
    JdbcCampgroundDao jdbcCampgroundDao;
    JdbcSiteDao jdbcSiteDao;
    JdbcReservationDao jdbcReservation;
    public CampgroundApplication(DataSource datasource) {

        this.jdbcParkDao = new JdbcParkDao(datasource);
        this.jdbcCampgroundDao = new JdbcCampgroundDao(datasource);
        this.jdbcSiteDao = new JdbcSiteDao(datasource);
        this.jdbcReservation = new JdbcReservationDao(datasource);
    }

    public void run() {
        UserInterface userInterface = new UserInterface();
        while(true) {
            int campgroundCommand = 3;
            while (campgroundCommand == 3) {
                String choice = userInterface.viewParksScreen(jdbcParkDao);
                if(choice.equalsIgnoreCase("Q")) {
                    break;
                }else {
                    userInterface.parkInformation(jdbcParkDao, Integer.parseInt(choice));
                    campgroundCommand = userInterface.parkInformationCommand();

                    if (campgroundCommand == 1) {
                        int reservationChoice = 2;
                        while (reservationChoice == 2) {
                            userInterface.viewCampgrounds(jdbcCampgroundDao,jdbcParkDao,Integer.parseInt(choice));
                            reservationChoice = userInterface.campgroundInformationCommand();

                            if(reservationChoice == 1) {
                                String[] info = userInterface.chooseCampground();
                                int campChoice = Integer.parseInt(info[0]);

                                while(campChoice != 0) {
                                    boolean hasMatch = false;
                                    while (!hasMatch) {
                                        String[] date = info[1].split("/");
                                        LocalDate fromDate = LocalDate.of(Integer.parseInt(date[2]),
                                                Integer.parseInt(date[0]), Integer.parseInt(date[1]));
                                        date = info[2].split("/");
                                        LocalDate toDate = LocalDate.of(Integer.parseInt(date[2]),
                                                Integer.parseInt(date[0]), Integer.parseInt(date[1]));
                                        hasMatch = userInterface.displayCampgroundSiteAvailability(jdbcCampgroundDao, jdbcSiteDao, campChoice, fromDate, toDate);

                                        if(hasMatch) {

                                            int site = userInterface.displayRegistration();

                                            if (site == 0) {
                                                break;
                                            } else {
                                                userInterface.registration(jdbcReservation, fromDate, toDate, site);
                                                campChoice = 0;
                                            }
                                        }
                                    }
                                }
                            } else {
                                userInterface.parkInformation(jdbcParkDao, Integer.parseInt(choice));
                                campgroundCommand = userInterface.parkInformationCommand();
                                continue;
                            }
                        }
                    } else if (campgroundCommand == 2) {
                        //res
                    } else {
                        continue;
                    }
                }

            }
            break;
        }
    }
}
