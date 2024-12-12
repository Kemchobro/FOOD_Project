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


    public static void addStrings(String... string) {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            writer.writeNext(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void clearHistory() {
        String csvFile = "src/main/java/org/FOOD/RecentHistory.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Overwrites the file with an empty content (clears the history)
            writer.writeAll(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
