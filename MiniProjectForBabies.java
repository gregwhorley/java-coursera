
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
    public int getRank(int year, String name, String gender) {
        //This method returns the 'rank' of the name in the file for the given gender, where rank 1 is the
        //name with the largest number of births
        int result = 0;
        return result;
    }
}
