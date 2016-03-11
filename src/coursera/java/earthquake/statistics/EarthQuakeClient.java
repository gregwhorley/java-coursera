import java.util.ArrayList;

public class EarthQuakeClient
{

    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry quakeEntry : quakeData) {
            if (quakeEntry.getMagnitude() > magMin) {
                answer.add(quakeEntry);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry quakeEntry : quakeData) {
            if (quakeEntry.getLocation().distanceTo(from) < distMax) {
                answer.add(quakeEntry);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry quakeEntry : quakeData) {
            if (quakeEntry.getDepth() > minDepth && quakeEntry.getDepth() < maxDepth) {
                filtered.add(quakeEntry);
            }
        }
        
        return filtered;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry quakeEntry : quakeData) {
            if (where.equalsIgnoreCase("start") && quakeEntry.getInfo().startsWith(phrase)) {
                filtered.add(quakeEntry);
            }
            else if (where.equalsIgnoreCase("end") && quakeEntry.getInfo().endsWith(phrase)) {
                filtered.add(quakeEntry);
            }
            else if (where.equalsIgnoreCase("any") && quakeEntry.getInfo().contains(phrase)) {
                filtered.add(quakeEntry);
            }
        }
        return filtered;
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
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> phrasedQuakes = filterByPhrase(list, where, phrase);
        for (QuakeEntry quakeEntry : phrasedQuakes) {
            System.out.println(quakeEntry.toString());
        }
        System.out.println("Found "+phrasedQuakes.size()+" that match "+phrase+" at "+where);
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredList = filterByMagnitude(list,5.0);
        for (QuakeEntry quakeEntry : filteredList) {
            System.out.println(quakeEntry);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);       
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000000.0, city);
        for (QuakeEntry quakeEntry : closeQuakes) {
            System.out.println(quakeEntry.getLocation().distanceTo(city)/1000+" "+quakeEntry.getInfo());
        }
        System.out.println("Found "+closeQuakes.size()+" quakes that match that criteria.");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double min = -4000.0;
        double max = -2000.0;
        ArrayList<QuakeEntry> quakeDepths = filterByDepth(list, min, max);
        System.out.println("Find quakes with depth between "+min+" and "+max);
        for (QuakeEntry quakeEntry : quakeDepths) {
            System.out.println(quakeEntry.toString());
        }
        System.out.println("Found "+quakeDepths.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
}
