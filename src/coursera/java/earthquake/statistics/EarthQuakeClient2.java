import java.util.ArrayList;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0,4.0));
        maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new PhraseFilter("any","o"));
        
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        ArrayList<QuakeEntry> filteredList  = eqc.filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found "+filteredList.size()+" quakes matching all filters.");
        System.out.println("Filters used: "+maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        Location location = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(location,3000000.0));
        maf.addFilter(new PhraseFilter("any","e"));
        
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        ArrayList<QuakeEntry> filteredList  = eqc.filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found "+filteredList.size()+" quakes matching all filters.");
        System.out.println("Filters used: "+maf.getName());
    }

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        //Filter f = new MinMagFilter(4.0);
        Filter filter1 = new MagnitudeFilter(3.5,4.5);
        Filter filter2 = new DepthFilter(-55000.0,-20000.0);
        //Location denver = new Location(39.7392, -104.9903);
        //Filter filter1 = new DistanceFilter(denver, 1000000.00);
        //Filter filter2 = new PhraseFilter("end","a");
        ArrayList<QuakeEntry> filteredList  = eqc.filter(list, filter1);
        ArrayList<QuakeEntry> finalList = eqc.filter(filteredList,filter2);
        for (QuakeEntry qe: finalList) { 
            System.out.println(qe);
        } 
        System.out.println("Quakes found: "+finalList.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
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
