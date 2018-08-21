package com.example.roronoazoro.simpleweatherapp;

import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date1);
        calendar2.setTime(date2);
        if (calendar.get(Calendar.DAY_OF_WEEK) == calendar2.get(Calendar.DAY_OF_WEEK)) {
            return true;
        }
        return false;
    }
}

