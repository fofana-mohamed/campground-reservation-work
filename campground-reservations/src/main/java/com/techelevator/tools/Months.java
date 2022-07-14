package com.techelevator.tools;

import java.util.HashMap;
import java.util.Map;

public class Months {
    private final Map<Integer, String> MONTHS_OF_THE_YEAR_NUMBER;
    private final Map<String, Integer> MONTHS_OF_THE_YEAR_NAME;

    public Months() {
        MONTHS_OF_THE_YEAR_NUMBER = new HashMap<>();
        MONTHS_OF_THE_YEAR_NUMBER.put(1,"January");
        MONTHS_OF_THE_YEAR_NUMBER.put(2,"February");
        MONTHS_OF_THE_YEAR_NUMBER.put(3,"March");
        MONTHS_OF_THE_YEAR_NUMBER.put(4,"April");
        MONTHS_OF_THE_YEAR_NUMBER.put(5,"May");
        MONTHS_OF_THE_YEAR_NUMBER.put(6,"June");
        MONTHS_OF_THE_YEAR_NUMBER.put(7,"July");
        MONTHS_OF_THE_YEAR_NUMBER.put(8,"August");
        MONTHS_OF_THE_YEAR_NUMBER.put(9,"September");
        MONTHS_OF_THE_YEAR_NUMBER.put(10,"October");
        MONTHS_OF_THE_YEAR_NUMBER.put(11,"November");
        MONTHS_OF_THE_YEAR_NUMBER.put(12,"December");

        MONTHS_OF_THE_YEAR_NAME = new HashMap<>();
        MONTHS_OF_THE_YEAR_NAME.put("January",1);
        MONTHS_OF_THE_YEAR_NAME.put("February",2);
        MONTHS_OF_THE_YEAR_NAME.put("March",3);
        MONTHS_OF_THE_YEAR_NAME.put("April",4);
        MONTHS_OF_THE_YEAR_NAME.put("May",5);
        MONTHS_OF_THE_YEAR_NAME.put("June",6);
        MONTHS_OF_THE_YEAR_NAME.put("July",7);
        MONTHS_OF_THE_YEAR_NAME.put("August",8);
        MONTHS_OF_THE_YEAR_NAME.put("September",9);
        MONTHS_OF_THE_YEAR_NAME.put("October",10);
        MONTHS_OF_THE_YEAR_NAME.put("November",11);
        MONTHS_OF_THE_YEAR_NAME.put("December",12);
    }

    public String getMonthByNumber(int month) {
        return MONTHS_OF_THE_YEAR_NUMBER.get(month);
    }
    public int getMonthByName(String month) {
        return MONTHS_OF_THE_YEAR_NAME.get(month);
    }
}
