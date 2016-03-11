
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class LargestQuakes {
    public void findLargestQuakes() {
       EarthQuakeParser parser = new EarthQuakeParser();
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       /*
       for (QuakeEntry quakeEntry : list) {
           System.out.println(quakeEntry.toString());
       }
       */
       /*
       int indexOfLargest = indexOfLargest(list);
       System.out.println("Found highest magnitude at location "+indexOfLargest);
       System.out.println(list.get(indexOfLargest).toString());
       */
       ArrayList<QuakeEntry> largestQuakes = getLargest(list,50);
       for (QuakeEntry quakeEntry : largestQuakes) {
           System.out.println(quakeEntry.toString());
        }
       System.out.println("read data for "+list.size()+" quakes");
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double highestMag = 0.0;
        int indexOfLargest = 0;
        for (int index=0;index<data.size();index++) {
            QuakeEntry quakeEntry = data.get(index);
            if (quakeEntry.getMagnitude() > highestMag) {
                highestMag = quakeEntry.getMagnitude();
                indexOfLargest = index;
            }
        }
        return indexOfLargest;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<QuakeEntry>();
        for (int counter=0;counter<howMany;counter++) {
            int indexOfLargest = indexOfLargest(quakeData);
            largestQuakes.add(quakeData.get(indexOfLargest));
            quakeData.remove(quakeData.get(indexOfLargest));
        }
        return largestQuakes;
    }
}
