package org.FOOD;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelpers {


    private static Instant getDateFromString(String date)
    {
        Instant timestamp = null;

        // Parsing the string to Date
        timestamp = Instant.parse(date);

        // Returning the converted timestamp
        return timestamp;
    }

    public static String getDate(String date)  {
        try {
            Instant timestamp = getDateFromString(date);

            // Printing the converted date

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
                    .withZone(ZoneId.systemDefault());
            String customFormatted = formatter.format(timestamp);
            return "Date: " + customFormatted;



        }

        // Catch block to handle exceptions
        catch (DateTimeParseException e) {

            // Throws DateTimeParseException if the string cannot be parsed
            System.out.println("Exception: " + e);
        }
        return date;
    }
}
