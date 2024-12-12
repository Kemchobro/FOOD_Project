package org.FOOD;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class RecentHistory {


    public static void addStrings(String... string) {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            writer.writeNext(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
