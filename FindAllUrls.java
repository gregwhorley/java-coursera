
/**
 * Write a description of FindAllUrls here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class FindAllUrls {
    public StorageResource findURLs(String url) {
        URLResource grabber = new URLResource(url);
        StorageResource store = new StorageResource();
        for (String line : grabber.lines()) {
            if (line.contains("http")) {
               int startPos = line.indexOf("http");
               int endPos = line.indexOf("\"",line.indexOf("http"));
               store.add(line.substring(startPos,endPos));
            }
        }
        return store;
    }
    public void testGrabber() {
        //StorageResource testURL = findURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        URLResource testURL = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : testURL.lines()) {
            System.out.println(item);
        }
    }
    public void testURLWithStorage() {
        StorageResource printUrl = findURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        int httpsLinks = 0;
        int dotComs = 0;
        int endComs = 0;
        int numDots = 0;
        for (String item : printUrl.data()) {
            System.out.println(item);
            if (item.contains("https")) {
                httpsLinks = httpsLinks + 1;
            }
            if (item.contains(".com")) {
                dotComs = dotComs + 1;
            }
            if (item.endsWith(".com")) {
                endComs = endComs + 1;
            }
            else if (item.endsWith(".com/")) {
                endComs = endComs + 1;
            }
            int marker = 0;
            while (item.indexOf(".",marker) != -1) {
                numDots = numDots + 1;
                marker = (item.indexOf(".",marker) + 1);
            }
        }
        System.out.println("Total number of URLs: " + printUrl.size());
        System.out.println("Total number of secure URLs: " + httpsLinks);
        System.out.println("Total links that contain .com: "  + dotComs);
        System.out.println("Total links that end with .com: " + endComs);
        System.out.println("Total number of dots found: " + numDots);
    }
}
