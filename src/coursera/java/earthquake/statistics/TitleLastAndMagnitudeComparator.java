
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] qe1Last = qe1.getInfo().split("\\W");
        String[] qe2Last = qe2.getInfo().split("\\W");
        if (qe1Last[qe1Last.length-1].compareTo(qe2Last[qe2Last.length-1]) == 0) {
            return Double.compare(qe1.getMagnitude(),qe2.getMagnitude());
        }
        return qe1Last[qe1Last.length-1].compareTo(qe2Last[qe2Last.length-1]);
    }
}
