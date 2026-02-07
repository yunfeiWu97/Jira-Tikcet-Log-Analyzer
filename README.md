# Jira Ticket Log Analyzer

A small Java CLI tool to analyze a Jira export CSV and print basic stats.

## How to run
1. Build
   - mvn test
   - mvn package

2. Run
   - java -jar target/jira-ticket-log-analyzer-1.0.0.jar --input sample-data/issues-sample.csv

## Example output
Input file: sample-data/issues-sample.csv
Issue rows: 5
First issue: ABC-1 | Done | User cannot login to VPN
=== Status summary ===
Done: 3
To Do: 1
In Progress: 1
Total: 5