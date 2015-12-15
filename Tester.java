
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        String filename = "weblog2-short_log";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        logAnalyzer.printAll();
    }
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+uniqueIPs+" unique IPs");
    }
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> uniqueVisits1 = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Number of unique IPs logged: "+uniqueVisits1.size());
        System.out.println("Unique IPs visited on Mar 24:");
        for (String s : uniqueVisits1) {
            System.out.println(s);
        }
    }
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int count2 = la.countUniqueIPsInRange(300,399);
        System.out.println("Unique IPs with status code in range 300,399\n"+count2);
    }
}
