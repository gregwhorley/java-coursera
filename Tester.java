
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String testString = "this is a test yes this is a test.";
        markov.setTraining(testString);
        ArrayList<String> follows = markov.getFollows("t.");
        System.out.println(follows);
        System.out.println(follows.size());
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		markov.setTraining(st);
		ArrayList<String> follows = markov.getFollows("t");
		System.out.println(follows.size());
    }
}
