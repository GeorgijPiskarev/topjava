package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T> boolean isBetween(T lt, T startTime, T endTime) {
        if (lt instanceof LocalTime) {
            return ((LocalTime) lt).compareTo((LocalTime) startTime) >= 0 && ((LocalTime) lt).compareTo((LocalTime) endTime) < 0;
        } else if (lt instanceof LocalDate) {
            return ((LocalDate) lt).compareTo((LocalDate) startTime) >= 0 && ((LocalDate) lt).compareTo((LocalDate) endTime) <= 0;
        } else {
            return false;
        }
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

