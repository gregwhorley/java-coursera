
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fileResource = new FileResource(filename);
         for (String line : fileResource.lines()) {
             LogEntry parseEntry = WebLogParser.parseEntry(line);
             records.add(parseEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int currentStatusCode = le.getStatusCode();
             if (currentStatusCode > num) {
                 System.out.println(le);
             }
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String accessTime = le.getAccessTime().toString();
             String ipAddress = le.getIpAddress();
             if (accessTime.contains(someday) && !uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs;
     }
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if (!uniqueIPs.contains(ipAddress) && low <= statusCode && statusCode <= high) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
}
