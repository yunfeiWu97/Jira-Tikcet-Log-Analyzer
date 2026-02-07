package com.yunfei.jiraanalyzer;

public class App {
    public static void main(String[] args) {
        String inputPath = getArgValue(args, "--input");
        if (inputPath == null) {
            System.out.println("Usage: java -jar target/jira-ticket-log-analyzer-1.0.0.jar --input <csv-path>");
            return;
        }

        System.out.println("Input file: " + inputPath);
    }

    private static String getArgValue(String[] args, String key) {
        for (int index = 0; index < args.length - 1; index++) {
            if (args[index].equals(key)) {
                return args[index + 1];
            }
        }
        return null;
    }
}
