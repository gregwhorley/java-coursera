
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
    public CSVParser getParserFromFile(int year) {
        FileResource fr = new FileResource("us_babynames_test/yob" + year + ".csv");
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
    public long getRank(int year, String name, String gender) {
        //This method returns the 'rank' of the name in the file for the given gender, where rank 1 is the
        //name with the largest number of births. Names not found return -1.
        //CSV files are structured by highest-to-lowest number of female names followed by 
        //highest-to-lowest number of male names
        long result = 0;
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
            result = -1;
        }
        else if (gender.equals("M")) {
            result -= maleRankingBoundary;
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
        name = "Mason";
        gender = "M";
        System.out.println(name + "/" + gender + " ranked " + getRank(year, name, gender) + " in " + year);
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
    public void testGetName() {
        int year = 2016;
        int rank = 1;
        String gender = "F";
        System.out.println("Rank " + rank + " for gender " + gender + " in year " 
                           + year + " is name: " + getName(year,rank,gender));
        gender = "M";
        System.out.println("Rank " + rank + " for gender " + gender + " in year " 
                           + year + " is name: " + getName(year,rank,gender));
        rank = 3;
        System.out.println("Rank " + rank + " for gender " + gender + " in year " 
                           + year + " is name: " + getName(year,rank,gender));
        rank = 69;
        System.out.println("Rank " + rank + " for gender " + gender + " in year " 
                           + year + " is name: " + getName(year,rank,gender));
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        //Determines what name would have been named if they were born in a different year,
        //based on the same popularity. You should determine the rank of name in the year
        //they were born, and then print the name born in newYear that is at the same rank
        //and same gender
        
        //Given a name and a year, I can use getRank() to get rank for their birth year
        Long ranking = getRank(year,name,gender);
        int rankInt = ranking.intValue();
        
        //Given a rank and a year, I can use getName() to get same-ranked name for newYear
        String rankedName = getName(newYear,rankInt,gender);
        
        System.out.println(name + " born in " + year + " would be named " + rankedName + " if they were born in " + newYear);
    }
}
