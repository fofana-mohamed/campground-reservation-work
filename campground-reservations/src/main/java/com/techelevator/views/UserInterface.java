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

    public static String getHomeScreenSelection(JdbcParkDao jdbcParkDao) {
        return " ";
    }
}
