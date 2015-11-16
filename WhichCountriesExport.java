/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

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
        
        //Each time you want to use the parser with another method, you will need to reset the parser by uncommenting the line below
        //parser = fr.getCSVParser();
    }
    public String countryInfo(CSVParser parser, String country) {
        String result = "";
        for (CSVRecord record : parser) {
            if (record.get("Country") == country) {
                result = country;
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
}
