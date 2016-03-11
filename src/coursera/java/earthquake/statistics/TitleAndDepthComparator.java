
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        if (qe1.getInfo().compareTo(qe2.getInfo()) == 0) {
            return Double.compare(qe1.getDepth(),qe2.getDepth());
        }
        return qe1.getInfo().compareTo(qe2.getInfo());
    }
}
