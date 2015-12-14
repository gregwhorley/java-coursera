
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
         // complete method to create a FileResource and iterate over all the lines in the file
         FileResource fileResource = new FileResource(filename);
         // For each line, create a LogEntry and store it in records
         //  Use WebLogParser.parseEntry to return a LogEntry with parsed data
         //  Add the returned LogEntry to records
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
     
     
}
