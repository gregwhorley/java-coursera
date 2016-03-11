
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for (int index=from+1;index<quakeData.size();index++) {
            if (quakeData.get(index).getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = index;
            }
        }
        return maxIndex;
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        //for (int index=0;index<in.size();index++) {
        for (int index=0;index<70;index++) {
            int maxIndex = getLargestDepth(in,index);
            QuakeEntry currentQuakeEntry = in.get(index);
            QuakeEntry quakeMax = in.get(maxIndex);
            in.set(index,quakeMax);
            in.set(maxIndex,currentQuakeEntry);
        }
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int index=0;index<numSorted;index++) {
            //if index+1 is less than index
            if (quakeData.get(index+1).getMagnitude() < quakeData.get(index).getMagnitude()) {
             //swap item at location index+1 with index
                QuakeEntry bigEntry = quakeData.get(index);
                QuakeEntry smallEntry = quakeData.get(index+1);
                quakeData.set(index+1,bigEntry);
                quakeData.set(index,smallEntry);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int index=in.size()-1;index>0;index--) {
            onePassBubbleSort(in, index);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        for (int index=0;index<quakeData.size()-1;index++) {
            if (quakeData.get(index).getMagnitude() > quakeData.get(index+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numPasses = 0;
        for (int index=in.size()-1;index>0;index--) {
            onePassBubbleSort(in, index);
            numPasses++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Number of passes to sort: "+numPasses);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numPasses = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numPasses++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Number of passes to sort: "+numPasses);
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
        */
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        //System.out.println("\nSorted list\n");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
