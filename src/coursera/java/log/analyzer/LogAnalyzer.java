
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

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
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)) {
                 counts.put(ip,1);
             }
             else {
                 int currentCount = counts.get(ip);
                 counts.put(ip,currentCount+1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
         //returns the max number of visits to this website by a single IP
         int maxCount = 0;
         for (String key : counts.keySet()) {
             int currentCount = counts.get(key);
             if (currentCount > maxCount) {
                 maxCount = currentCount;
             }
         }
         return maxCount;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
         //returns an ArrayList<String> of IPs that all have the maximum number of visits
         // in the log
         ArrayList<String> ipList = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(counts);
         for (String key : counts.keySet()) {
             if (counts.get(key) == mostVisits) {
                 ipList.add(key);
             }
         }
         return ipList;
     }
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> ipByDay = new HashMap<String,ArrayList<String>>();
         
         for (LogEntry le : records) {
             String[] dateArray = le.getAccessTime().toString().split("\\ ");
             String dateInput = dateArray[1]+dateArray[2];
             String ipAddress = le.getIpAddress();
             if (!ipByDay.containsKey(dateInput)) {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(ipAddress);
                ipByDay.put(dateInput,newList);
             }
             else {
                ArrayList<String> currentIps = ipByDay.get(dateInput);
                currentIps.add(ipAddress);
                ipByDay.put(dateInput,currentIps);
             }
         }
         return ipByDay;
     }
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipByDay) {
         String dayWithMostVisits = "";
         int mostValues = 0;
         for (String key : ipByDay.keySet()) {
             if (ipByDay.get(key).size() > mostValues) {
                 mostValues = ipByDay.get(key).size();
                 dayWithMostVisits = key;
             }
         }
         return dayWithMostVisits;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipByDay,
                                                        String day) {
         ArrayList<String> ipsOnDay = ipByDay.get(day);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();

         for (int index=0; index < ipsOnDay.size(); index++) {
             String ipAddress = ipsOnDay.get(index);
             if (!counts.containsKey(ipAddress)) {
                 counts.put(ipAddress,1);
             }
             else {
                 int currentIpCount = counts.get(ipAddress);
                 counts.put(ipAddress,currentIpCount+1);
             }
         }
         return iPsMostVisits(counts);
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs() {
         /**
         *ArrayList<String> uniqueIPs = new ArrayList<String>();
         *for (LogEntry le : records) {
         *    String ipAddress = le.getIpAddress();
         *    if (!uniqueIPs.contains(ipAddress)) {
         *        uniqueIPs.add(ipAddress);
         *    }
         *}
         *return uniqueIPs.size();
         */
        HashMap<String,Integer> counts = countVisitsPerIP();
        return counts.size();
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
