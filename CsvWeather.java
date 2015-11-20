
/**
 * Write a description of CsvWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CsvWeather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow,smallestSoFar);
        }
        return smallestSoFar;
    }
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp) {
                smallestSoFar = currentRow;
            }
            // uncomment this else if block to return the last instance of the smallest temp
            //else if (currentTemp == smallestTemp) {
                //smallestSoFar = currentRow;
            //}
        }
        return smallestSoFar;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }
    public String fileWithColdestTemperature() {
        //get a list of files
        DirectoryResource dr = new DirectoryResource();
        //initialize variable for smallest temperature
        CSVRecord smallestSoFar = null;
        //initialize String that will contain filename to be returned
        String coldestFile = "";
        //trying this out for now until I have a better idea
        double smallest = 99.9;
        //loop through each selected file
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            //find smallest temperature in current file
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(current,smallestSoFar);
            //if smallest returned temp is lower than smallest, get the filename
            if (Double.parseDouble(smallestSoFar.get("TemperatureF")) < smallest) {
                coldestFile = file.getPath();
            }
        }
        return coldestFile;
    }
    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day from selected files: " + coldestFileName);
        FileResource fr = new FileResource(coldestFileName);
        CSVRecord smallestTemp = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + smallestTemp.get("TemperatureF"));
        System.out.println("All of the temperatures on the coldest day were:");
        for (CSVRecord current : fr.getCSVParser()) {
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord currentRow : parser) {
            lowestHumidityRow = getLowestHumidityOfTwo(currentRow,lowestHumidityRow);
        }
        return lowestHumidityRow;
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow, CSVRecord lowestHumidityRow) {
        if (lowestHumidityRow == null) {
                lowestHumidityRow = currentRow;
            }
            else {
               if (! currentRow.get("Humidity").equals("N/A")) {
                  int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                  int lowHumidity = Integer.parseInt(lowestHumidityRow.get("Humidity"));
                  if (currentHumidity < lowHumidity) {
                      lowestHumidityRow = currentRow;
                    }
               }
            }
        return lowestHumidityRow;
    }
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestRecord = null;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestRecord = getLowestHumidityOfTwo(currentRow,smallestRecord);
        }
        return smallestRecord;
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }
}
