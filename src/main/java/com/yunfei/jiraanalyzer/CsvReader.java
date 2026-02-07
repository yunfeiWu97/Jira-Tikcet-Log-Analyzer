package com.yunfei.jiraanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Issue> readIssues(String filePath) throws IOException {
        List<Issue> issues = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // header
            if (line == null) {
                return issues;
            }

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] cols = line.split(",", -1);

                String key = cols.length > 0 ? cols[0].trim() : "";
                String summary = cols.length > 1 ? cols[1].trim() : "";
                String status = cols.length > 2 ? cols[2].trim() : "";

                if (key.isEmpty()) {
                    continue;
                }
                
                issues.add(new Issue(key, summary, status));
            }
        }

        return issues;
    }
}
