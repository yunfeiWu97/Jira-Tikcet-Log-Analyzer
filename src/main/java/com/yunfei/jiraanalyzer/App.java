package com.yunfei.jiraanalyzer;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String inputPath = getArgValue(args, "--input");
        if (inputPath == null || inputPath.isBlank()) {
            System.out.println("Missing --input");
            System.out.println("Example:");
            System.out.println("java -jar target/jira-ticket-log-analyzer-1.0.0.jar --input sample-data/issues-sample.csv");
            return;
        }

        System.out.println("Input file: " + inputPath);

        CsvReader csvReader = new CsvReader();

        try {
            List<Issue> issues = csvReader.readIssues(inputPath);
            System.out.println("Issue rows: " + issues.size());

            if (!issues.isEmpty()) {
                System.out.println("First issue: " + issues.get(0));
            }
        } catch (IOException ex) {
            System.out.println("Could not read file: " + ex.getMessage());
        }
    }
        
    private static String getArgValue(String[] args, String key) {
        if (args == null) {
            return null;
        }

        for (int index = 0; index < args.length; index++) {
            if (key.equals(args[index]) && index + 1 < args.length) {
                return args[index + 1];
            }
        }

        return null;
    }
}
