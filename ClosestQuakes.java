
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes
{
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        /*
         * This method has three parameters, an ArrayList of type QuakeEntry named quakeData, 
         * a Location named current, and an int named howMany. This method should find the closest 
         * number of howMany earthquakes to the current Location and return them in an ArrayList of 
         * type QuakeEntry. The earthquakes should be in the ArrayList in order with the closest 
         * earthquake in index position 0. If there are fewer than howMany earthquakes in quakeData, 
         * then the ArrayList returned would be the same size as quakeData.
         */
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int j=0;j < howMany;j++) {
            int minIndex = 0;
            for (int index=1; index < quakeData.size();index++) {
                QuakeEntry quake = quakeData.get(index);
                Location location = quake.getLocation();
                if (location.distanceTo(current) < 
                    quakeData.get(minIndex).getLocation().distanceTo(current)) {
                    
                    minIndex = index;
                }
            }
            ret.add(quakeData.get(minIndex));
            quakeData.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
}
