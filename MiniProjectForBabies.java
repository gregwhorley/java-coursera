
/**
 * Write a description of MiniProjectForBabies here.
 * 
 * @author Greg
 * @version 11/23/15
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class MiniProjectForBabies {
    public void totalBirths(FileResource fileResource) {
        int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		StorageResource boysNames = new StorageResource();
		StorageResource girlsNames = new StorageResource();
		int totalNames = 0;
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
		}
		System.out.println("Unique girl's names in the file: ");
		for (String item : girlsNames.data()) {
		    System.out.println(item);
		}
		System.out.println("Total number of unique names in the file = " + totalNames);
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public long getRank(int year, String name, String gender) {
        //This method returns the 'rank' of the name in the file for the given gender, where rank 1 is the
        //name with the largest number of births. Names not found return -1.
        //CSV files are structured by highest-to-lowest number of female names followed by 
        //highest-to-lowest number of male names
        long result = 0;
        FileResource fileResource = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fileResource.getCSVParser(false);
        long maleRankingBoundary = 0;
        boolean maleRankFound = false;
        //For each row of the CSV
        for (CSVRecord currentRow : parser) {
           //If gender is M and it is the first M row
           if (currentRow.get(1).equals("M") && maleRankFound == false) {
              //(Current line - 1) is boundary for male ranking
              maleRankingBoundary = (parser.getCurrentLineNumber() - 1);
              maleRankFound = true;
           }
           //Are the Name and Gender fields the same as what's specified?
           if (currentRow.get(0).equals(name) && currentRow.get(1).equals(gender)) {
              //If gender specified as F
              if (gender.equals("F")) {
                 //Rank is whatever the line number is
                 result = parser.getCurrentLineNumber();
                 //Break out of loop
                 break;
              }
              //If gender specified as M
              else {
                 //Rank is line number - (line number of last instance of F)
                 result = parser.getCurrentLineNumber() - maleRankingBoundary;
                 break;
              }
           }
        }
        if ( result == 0 ) {
            result = -1;
        }
        return result;
    }
    public void testGetRank() {
        int year = 2016;
        String name = "Emma";
        String gender = "F";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
        name = "Noah";
        gender = "M";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
        name = "NoName";
        gender = "M";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
        name = "Ava";
        gender = "M";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
        name = "Liam";
        gender = "F";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
        name = "Mason";
        gender = "M";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
    }
}
