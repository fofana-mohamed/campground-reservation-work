package com.techelevator.views;

import com.techelevator.models.dao.JdbcParkDao;
import com.techelevator.models.dto.Park;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner in = new Scanner(System.in);

    public static void setIn(InputStream inStream)
    {
        in = new Scanner(inStream);
    }

    public static String getHomeScreenSelection(JdbcParkDao jdbcParkDao){

        System.out.println("View Parks Interface");
        System.out.println("Select a Park for Further Details");

        List<Park> parks = jdbcParkDao.getAllParks();

        for (Park park : parks) {
            int id = park.getParkId();
            String name = park.getName();

            System.out.println(id + ") " + name);
        }

        System.out.println("X) Quit");
        String choice = in.nextLine().trim().toLowerCase();

        boolean isValidChoice = (choice.equalsIgnoreCase("X")) || ((Integer.parseInt(choice) > 0)
                && (Integer.parseInt(choice) <= parks.size()));

        if (isValidChoice) {
            if (!choice.equalsIgnoreCase("X")) {
                displayParkDetail(parks.get(Integer.parseInt(choice) - 1));
            } else {
                return "Done";
            }
        } else {
            System.out.println("Invalid Selection. Please Try again...");
            System.out.flush();
            getHomeScreenSelection(jdbcParkDao);
        }
//        switch (choice) {
//            case "1":
//                return "list";
//            case "2":
//                return "reserve";
//            default:
//                return "invalid";
//        }
        return "";
    }
//    public static String homeScreenCommand() {
//
//    }

    public static void displayParkDetail(Park park) {
        System.out.println("Park Information Screen");
        System.out.println("------------------------");
        System.out.println(park.getName());
        System.out.println("Location:           " + park.getLocation());
        System.out.println("Established:        " + park.getDate());
        System.out.println("Area                " + park.getArea());
        System.out.println("Annual Visitors     " + park.getVisitors());
        System.out.println();
        System.out.println();
        System.out.println(park.getDescription());
    }
    public static String parkCommandScreen() {
        return "";
    }
}
