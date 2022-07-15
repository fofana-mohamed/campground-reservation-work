package com.techelevator.views;

import com.techelevator.models.dao.JdbcCampgroundDao;
import com.techelevator.models.dao.JdbcParkDao;
import com.techelevator.models.dao.JdbcReservationDao;
import com.techelevator.models.dao.JdbcSiteDao;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;
import com.techelevator.tools.Months;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner in = new Scanner(System.in);

    public static void setIn(InputStream inStream)
    {
        in = new Scanner(inStream);
    }

    public static String viewParksScreen(JdbcParkDao jdbcParkDao) {
        System.out.println("View Park Interface");
        System.out.println("Select a Park for Further Details");

        List<Park> parks = jdbcParkDao.getAllParks();

        for (Park park : parks) {
            int id = park.getParkId();
            String name = park.getName();

            System.out.println(id + ") " + name);
        }
        System.out.println("Q) Quit");
        System.out.println();
        System.out.print("Please make a selection --> ");
        String choice = in.nextLine();

        return choice;
    }

    public static void parkInformation(JdbcParkDao jdbcParkDao, int parkID) {
        Park park = jdbcParkDao.getPark(parkID);
        System.out.println();
        System.out.println("Park Information Screen ");

        System.out.println(park.getName());
        System.out.println("Location:         " + park.getLocation());
        System.out.println("Park ID:          " + park.getParkId());
        System.out.println("Established:      " + park.getDate());
        System.out.println("Area:             " + park.getArea());
        System.out.println("Annual Visitors:  " + park.getVisitors());
        System.out.println();
        System.out.println(park.getDescription());
        System.out.println();
    }

    public static int parkInformationCommand() {
        System.out.println("Select a Command");
        System.out.println("1) View Campgrounds");
        System.out.println("2) Search for Reservation");
        System.out.println("3) Go back to previous screen");
        System.out.print("--> ");
        int choice = Integer.parseInt(in.nextLine());

        return choice;
    }

    public static void viewCampgrounds(JdbcCampgroundDao jdbcCampgroundDao,
                        JdbcParkDao jdbcParkDao, int parkID) {

        System.out.println();
        System.out.println(jdbcParkDao.getPark(parkID).getName() + " Campgrounds");
        System.out.format("%15s%15s%15s%15s%n", "Name", "Open", "Close", "Daily Fee");


        List<Campground> campgrounds = jdbcCampgroundDao.getCampgroundByParkID(parkID);
        Months months = new Months();

        for (Campground campground : campgrounds) {
            int id = campground.getCampgroundID();
            String name = campground.getName();
            String open = months.getMonthByNumber(campground.getOpenFrom());
            String close = months.getMonthByNumber(campground.getOpenTo());
            double fee = campground.getDailyFee();

            System.out.print("#" + id );
            System.out.format("%15s%15s%15s%15s%n", name, open, close, fee);
        }
    }
    public static int campgroundInformationCommand() {
        System.out.println();
        System.out.println("1) Search for available reservation");
        System.out.println("2) return to previous screen");
        System.out.println();
        System.out.print("--> ");
        int choice = Integer.parseInt(in.nextLine());

        return choice;
    }
    public static boolean displayCampgroundSiteAvailability(JdbcCampgroundDao campgroundDao, JdbcSiteDao jdbcSiteDao,
                                                         int campgroundID, LocalDate fromDate, LocalDate toDate) {
        List<Site> sites = jdbcSiteDao.getAvailableSitesByCampground(campgroundID,fromDate,toDate);
        int diff = toDate.compareTo(fromDate);
        double fee = campgroundDao.getCampground(campgroundID).getDailyFee();
        if (sites.size() ==  0) {
            System.out.println("There are no available dates...Please try another date");
            return false;
        } else {
            System.out.format("%15s","Results Matching your criteria");
            System.out.println();
            System.out.format("%15s%15s%15s%15s%15s%n", "Site No.", "Accessible?", "Max RV Length", "Utility", "Cost");



            for (Site site : sites) {
                int siteID = site.getSiteID();
                int max = site.getMaxOccupancy();
                boolean access = site.isAccessible();
                int maxRv = site.getMaxRVLength();
                boolean utilities = site.isUtilities();
                System.out.format("%12s%15s%13s%19s%15s%n", siteID, access,max,utilities, fee * diff);

            }
            return true;
        }

    }
    public static int chooseCampground() {
        System.out.println();
        System.out.print("Which campground --> ");
        int choice = Integer.parseInt(in.nextLine());

        return choice;
    }
    public static String[] getDates() {
        String[] info = new String[2];
        System.out.print("What is the arrival date --> ");
        info[0] = in.nextLine();
        System.out.print("What is the departure date --> ");
        info[1] = in.nextLine();

        return info;
    }
    public static void registration(JdbcReservationDao jdbcReservationDao, LocalDate fromDate, LocalDate toDate, int site) {
        System.out.print("What name should the reservation be made under --> ");
        String name = in.nextLine();

        LocalDate createDate = LocalDate.now();
        Reservation reservation = new Reservation(0,site,name,fromDate,toDate,createDate);
        reservation = jdbcReservationDao.addReservation(reservation);

        System.out.println("Reservation has been made and the confirmation id id: " + reservation.getReservationID());

    }
    public static int displayRegistration() {
        System.out.print("Which site should be reserved --> ");
        int site = Integer.parseInt(in.nextLine());

        return site;
    }

}
