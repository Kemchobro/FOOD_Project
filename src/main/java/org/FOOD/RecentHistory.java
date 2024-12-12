package org.FOOD;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecentHistory {

    // adds an entry to the end of the csv file
    public static void addStrings(String... string) {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            writer.writeNext(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // returns the entries on the csv file
    public static List<String[]> getStrings() {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        List<String[]> rows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            rows = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return rows;
    }
    // wipes the csv file
    public static void clearHistory() {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            writer.writeAll(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
