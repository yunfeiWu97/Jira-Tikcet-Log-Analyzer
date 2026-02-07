package com.yunfei.jiraanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CsvReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void readIssues_readsAllDataRows() throws IOException {
        Path csvFile = tempDir.resolve("issues.csv");

        String csv = ""
                + "Issue key,Summary,Status,Priority,Created,Resolved\n"
                + "ABC-1,User cannot login to VPN,Done,High,2026-02-01T09:10:00,2026-02-01T11:30:00\n"
                + "ABC-2,Printer not printing in Office,In Progress,Medium,2026-02-01T10:05:00,\n"
                + "ABC-3,Teams audio not working,Done,Low,2026-02-02T13:00:00,2026-02-02T15:10:00\n";

        Files.writeString(csvFile, csv);

        CsvReader reader = new CsvReader();
        List<Issue> issues = reader.readIssues(csvFile.toString());

        assertEquals(3, issues.size());
        assertEquals("ABC-1", issues.get(0).getKey());
        assertEquals("Done", issues.get(0).getStatus());
        assertEquals("User cannot login to VPN", issues.get(0).getSummary());
    }

    @Test
    void readIssues_skipsBlankLines() throws IOException {
        Path csvFile = tempDir.resolve("issues.csv");

        String csv = ""
                + "Issue key,Summary,Status,Priority,Created,Resolved\n"
                + "\n"
                + "ABC-1,Something,Done,High,2026-02-01T09:10:00,\n"
                + "   \n"
                + "ABC-2,Something else,To Do,Low,2026-02-01T10:05:00,\n";

        Files.writeString(csvFile, csv);

        CsvReader reader = new CsvReader();
        List<Issue> issues = reader.readIssues(csvFile.toString());

        assertEquals(2, issues.size());
    }

    @Test
    void readIssues_emptyFile_returnsEmptyList() throws IOException {
        Path csvFile = tempDir.resolve("empty.csv");
        Files.writeString(csvFile, "");

        CsvReader reader = new CsvReader();
        List<Issue> issues = reader.readIssues(csvFile.toString());

        assertTrue(issues.isEmpty());
    }
}
