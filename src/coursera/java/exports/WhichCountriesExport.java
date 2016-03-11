package coursera.java.exports;

/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //Each time you want to use the parser with another method, you will need 
        //to reset the parser by uncommenting the line below
        //parser = fr.getCSVParser();
        
        //System.out.println("Test with invalid country: America");
        //String invalidCountry = countryInfo(parser,"America");
        //System.out.println(invalidCountry);
        //parser = fr.getCSVParser();
        //System.out.println("Test with valid country: Madagascar");
        //String validCountry = countryInfo(parser,"Nauru");
        //System.out.println(validCountry);
        //listExportersTwoProducts(parser,"cotton","flowers");
        //int testNumberOfExporters = numberOfExporters(parser,"cocoa");
        //System.out.println("Number of cocoa exporting countries: " + testNumberOfExporters);
        bigExporters(parser,"$999,999,999,999");
        //tryIntParse();
    }
    public String countryInfo(CSVParser parser, String country) {
        String result = "";
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
               result = record.get("Country");
               result = result.concat(": ");
               result = result.concat(record.get("Exports"));
               result = result.concat(": ");
               result = result.concat(record.get("Value (dollars)"));
            }
        }
        if (result == "") {
           return "NOT FOUND";
        }
        else {
           return result;
        }
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String testRecord = record.get("Exports");
            if (testRecord.contains(exportItem1) && testRecord.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int exporterSum = 0;
        for (CSVRecord record : parser) {
            String testRecord = record.get("Exports");
            if (testRecord.contains(exportItem)) {
                exporterSum = exporterSum + 1;
            }
        }
        return exporterSum;
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String testRecord = record.get("Value (dollars)");
            if (testRecord.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + testRecord);
            }
        }
    }
    public void tryIntParse() {
        System.out.println("Simple test of Integer.parseInt()");
        String testString = "100";
        System.out.println("Converting \"100\" into an integer: " + Integer.parseInt(testString));
    }
    public void main(String args[]) {
        tester();
    }
}
