package com.yunfei.jiraanalyzer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IssueStats {

    public Map<String, Integer> countByStatus(List<Issue> issues) {
        Map<String, Integer> counts = new HashMap<>();

        for (Issue issue : issues) {
            String status = issue.getStatus();
            if (status == null || status.isBlank()) {
                status = "Unknown";
            }

            Integer current = counts.get(status);
            if (current == null) {
                counts.put(status, 1);
            } else {
                counts.put(status, current + 1);
            }
        }
        Map<String, Integer> sorted = new LinkedHashMap<>();
                counts.entrySet()
                    .stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(entry -> sorted.put(entry.getKey(), entry.getValue()));

        return sorted;
    }
}