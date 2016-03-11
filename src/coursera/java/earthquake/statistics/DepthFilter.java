
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if ((qe.getDepth() > minDepth && qe.getDepth() < maxDepth) || 
                (qe.getDepth() == minDepth || qe.getDepth() == maxDepth)) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return "Depth";
    }
}
