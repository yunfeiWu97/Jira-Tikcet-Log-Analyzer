package com.yunfei.jiraanalyzer;

public class App {
    public static void main(String[] args) {
        String inputPath = getArgValue(args, "--input");
        if (inputPath == null) {
            System.out.println("Usage: java -jar target/jira-ticket-log-analyzer-1.0.0.jar --input <csv-path>");
            return;
        }

        System.out.println("Input file: " + inputPath);

        CsvReader csvReader = new CsvReader();
            try {
                int rowCount = csvReader.countDataRows(inputPath);
                System.out.println("Rows (no header): " + rowCount);
            } catch (Exception ex) {
                System.out.println("Could not read file.");
                System.out.println(ex.getMessage());
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
