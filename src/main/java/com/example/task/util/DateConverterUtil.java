package com.example.task.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverterUtil {

    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate convertSimpleStringToLocalDate(String simpleDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(simpleDate, formatter);
    }

}
