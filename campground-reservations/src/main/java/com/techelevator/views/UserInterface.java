package com.techelevator.views;

import com.techelevator.models.dao.JdbcCampgroundDao;
import com.techelevator.models.dao.JdbcParkDao;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;

import java.io.InputStream;
import java.io.PrintStream;
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

        return " ";
    }

    public static void parkInformation(Park park) {
        System.out.println("Park Information: ");

        System.out.println("Name: " + park.getName());

        System.out.println("Park ID: " + park.getParkId());

        System.out.println("Location: " + park.getLocation());

        System.out.println("Established: " + park.getDate());

        System.out.println("Area: " + park.getArea());

        System.out.println("Annual Visitors: " + park.getVisitors());

        System.out.println("Description: " + park.getDescription());
    }

}
