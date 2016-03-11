package coursera.java.baby.names.project;
/**
 * Write a description of MiniProjectForBabies here.
 * 
 * @author Greg
 * @version 11/23/15
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class MiniProjectForBabies {
    public void totalBirths(FileResource fileResource) {
        int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		StorageResource boysNames = new StorageResource();
		StorageResource girlsNames = new StorageResource();
		int totalNames = 0;
		int uniqueBoys = 0;
		int uniqueGirls = 0;
		for (CSVRecord csvRecord : fileResource.getCSVParser(false)) {
			int numBorn = Integer.parseInt(csvRecord.get(2));
			totalBirths += numBorn;
			totalNames += 1;
			if (csvRecord.get(1).equals("M")) {
				totalBoys += numBorn;
				boysNames.add(csvRecord.get(0));
			}
			else {
				totalGirls += numBorn;
				girlsNames.add(csvRecord.get(0));
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("Unique boy's names in the file: ");
		for (String item : boysNames.data()) {
		    System.out.println(item);
		    uniqueBoys += 1;
		}
		System.out.println("Unique girl's names in the file: ");
		for (String item : girlsNames.data()) {
		    System.out.println(item);
		    uniqueGirls += 1;
		}
		System.out.println("Total number of unique names in the file = " + totalNames);
		System.out.println("Total unique girl's names " + uniqueGirls);
		System.out.println("Total unique boy's names " + uniqueBoys);
    }
    public CSVParser getParserFromFile(int year) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }
    public long getMaleRankingBoundary(CSVParser parser) {
        long result = 0;
        boolean maleRankFound = false;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get(1).equals("M") && maleRankFound == false) {
                result = (parser.getCurrentLineNumber() - 1);
                maleRankFound = true;
            }
        }
        return result;
    }
    public int getRank(int year, String name, String gender) {
        //This method returns the 'rank' of the name in the file for the given gender, where rank 1 is the
        //name with the largest number of births. Names not found return -1.
        //CSV files are structured by highest-to-lowest number of female names followed by 
        //highest-to-lowest number of male names
        Long result = 0L;
        CSVParser maleRankParser = getParserFromFile(year);
        long maleRankingBoundary = getMaleRankingBoundary(maleRankParser);
        CSVParser parser = getParserFromFile(year);
        for (CSVRecord currentRow : parser) {
           //Are the Name and Gender fields the same as what's specified?
           if (currentRow.get(0).equals(name) && currentRow.get(1).equals(gender)) {
              result = parser.getCurrentLineNumber();
              break;
           }
        }
        if (result == 0) {
            result = -1L;
        }
        else if (gender.equals("M")) {
            result -= maleRankingBoundary;
        }
        return result.intValue();
    }
    public String getName(int year, int rank, String gender) {
        //Returns the name of the person in the file at specified rank and gender. 
        //If rank does not exist in the file, then "NO NAME" is returned. CSV files 
        //are structured by female names in descending order of number of births followed
        //by male names in descending order
        String result = "";
        CSVParser maleRankParser = getParserFromFile(year);
        long maleRankingBoundary = getMaleRankingBoundary(maleRankParser);
        CSVParser parser = getParserFromFile(year);
        //Reassign rank to line number for Male names if gender is "M"
        if (gender.equals("M")) {
            rank += maleRankingBoundary;
        }
        for (CSVRecord currentRow : parser) {
            if (parser.getCurrentLineNumber() == rank && currentRow.get(1).equals(gender)) {
                result = currentRow.get(0);
                break;
            }
        }
        if (result.equals("")) {
            result = "NO NAME";
        }
        return result;
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        //Determines what name would have been named if they were born in a different year,
        //based on the same popularity. You should determine the rank of name in the year
        //they were born, and then print the name born in newYear that is at the same rank
        //and same gender
        
        //Given a name and a year, I can use getRank() to get rank for their birth year
        int ranking = getRank(year,name,gender);
        
        //Given a rank and a year, I can use getName() to get same-ranked name for newYear
        String rankedName = getName(newYear,ranking,gender);
        
        System.out.println(name + " born in " + year + " would be named " + rankedName + " if they were born in " + newYear);
    }
    public int yearOfHighestRank(String name, String gender) {
        //This method selects a range of files to process and returns an integer, the year
        //with the highest rank for the name and gender. If the name and gender are not in any
        //of the selected files, it should return -1.
        DirectoryResource directoryResource = new DirectoryResource();
        Integer highestRank = null;
        Integer yearOfHighestRank = null;
        for (File file : directoryResource.selectedFiles()) {
            int yearOfFile = Integer.parseInt(file.getName().substring(3,7));
            int currentRank = getRank(yearOfFile, name, gender);
            if ((highestRank == null && currentRank != -1) || (currentRank != -1 && currentRank < highestRank)) {
                highestRank = currentRank;
                yearOfHighestRank = yearOfFile;
            }
        }
        if (yearOfHighestRank == null) {
            yearOfHighestRank = -1;
        }
        return yearOfHighestRank;
    }
    public double getAverageRank(String name, String gender) {
        //This method selects a range of files to process and returns a double representing 
        //the average rank of the name and gender over the selected files. It should return 
        //-1.0 if the name is not ranked in any of the selected files.
        DirectoryResource directoryResource = new DirectoryResource();
        double averageRank = 0.0;
        int numberOfYears = 0;
        for (File file : directoryResource.selectedFiles()) {
            int yearOfFile = Integer.parseInt(file.getName().substring(3,7));
            int currentRank = getRank(yearOfFile, name, gender);
            if (currentRank != -1) {
                averageRank += (double) currentRank;
                numberOfYears += 1;
            }
        }
        if (averageRank == 0.0) {
            averageRank = -1.0;
        }
        else {
            averageRank /= (double) numberOfYears;
        }
        return averageRank;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        //This method returns an integer, the total number of births of those names 
        //with the same gender and same year who are ranked higher than name.
        int totalBirths = 0;
        //Open the file that corresponds to year
        CSVParser csvParser = getParserFromFile(year);
        //For each line of the file 
        for (CSVRecord currentRow : csvParser) {
          //Fast forward to first row of gender or to end of file
          if (! currentRow.get(1).equals(gender)) {
             continue;
          }
          //Capture the name and number of births
          String currentName = currentRow.get(0);
          int currentBirths = Integer.parseInt(currentRow.get(2));
          //If the name is the same as name
          if (name.equals(currentName)) {
             //break out of loop
             break;
          }
          //Or else
          else {
             //add number of births to current tally
             totalBirths += currentBirths;
          }
        }
        return totalBirths;
    }
    public void getTotalBirthsByYear(String name) {
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            int year = Integer.parseInt(file.getName().substring(3,7));
            int totalBirths = 0;
            CSVParser csvParser = getParserFromFile(year);
            for (CSVRecord currentRow : csvParser) {
                if (currentRow.get(0).equals(name)) {
                    totalBirths = Integer.parseInt(currentRow.get(2));
                }
            }
            System.out.println("Total births for " + name + " in " + year + " = " + totalBirths);
        }
    }
    public void yearOfMostBirths(String name) {
        DirectoryResource directoryResource = new DirectoryResource();
        int yearOfMostBirths = 0;
        int highestBirths = 0;
        for (File file : directoryResource.selectedFiles()) {
            int year = Integer.parseInt(file.getName().substring(3,7));
            int totalBirths = 0;
            CSVParser csvParser = getParserFromFile(year);
            for (CSVRecord currentRow : csvParser) {
                if (currentRow.get(0).equals(name)) {
                    totalBirths = Integer.parseInt(currentRow.get(2));
                }
            }
            if (totalBirths > highestBirths) {
                yearOfMostBirths = year;
                highestBirths = totalBirths;
            }
        }
        System.out.println(name + " had most births in " + yearOfMostBirths);
    }
}
