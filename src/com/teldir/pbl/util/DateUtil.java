package com.teldir.pbl.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by arvindhn602 on 3/16/2017.
 */
public class DateUtil {
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date){
        if(date == null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String DateString){
        try {
            return DATE_FORMATTER.parse(DateString, LocalDate::from);
        }
        catch(DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String DateString){
        return DateUtil.parse(DateString)!=null;
    }
}
