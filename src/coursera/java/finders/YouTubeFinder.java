
/**
 * Write a description of YouTubeFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.URLResource;

public class YouTubeFinder {
    public void youTubeLink() {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String line: url.words()) {
            String lineToLower = line.toLowerCase();
            if (lineToLower.contains("youtube.com")) {
                int firstQuote = lineToLower.indexOf("\"");
                int secondQuote = lineToLower.indexOf("\"",firstQuote+1);
                System.out.println(line.substring(firstQuote+1,secondQuote));
            }
        }
    }
}
