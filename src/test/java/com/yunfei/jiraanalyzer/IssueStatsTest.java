package com.yunfei.jiraanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class IssueStatsTest {

    @Test
    void countByStatus_countsEachStatus() {
        List<Issue> issues = List.of(
                new Issue("A-1", "one", "Done"),
                new Issue("A-2", "two", "Done"),
                new Issue("A-3", "three", "To Do")
        );

        IssueStats stats = new IssueStats();
        Map<String, Integer> counts = stats.countByStatus(issues);

        assertEquals(2, counts.get("Done"));
        assertEquals(1, counts.get("To Do"));
    }

    @Test
    void countByStatus_blankStatus_goesToUnknown() {
        List<Issue> issues = List.of(
                new Issue("A-1", "one", ""),
                new Issue("A-2", "two", "   "),
                new Issue("A-3", "three", null)
        );

        IssueStats stats = new IssueStats();
        Map<String, Integer> counts = stats.countByStatus(issues);

        assertEquals(3, counts.get("Unknown"));
    }
}
