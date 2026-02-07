package com.yunfei.jiraanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public int countDataRows(String filePath) throws IOException {
        int rowCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // header
            if (line == null) {
                return 0;
            }

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rowCount++;
                }
            }
        }

        return rowCount;
    }
}
