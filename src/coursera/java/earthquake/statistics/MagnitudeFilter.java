
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if ((qe.getMagnitude() > minMag && qe.getMagnitude() < maxMag) || 
                (qe.getMagnitude() == minMag || qe.getMagnitude() == maxMag)) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return "Magnitude";
    }
}
