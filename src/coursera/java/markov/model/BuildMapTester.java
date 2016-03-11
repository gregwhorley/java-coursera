
/**
 * Write a description of BuildMapTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.HashMap;

public class BuildMapTester {
    public void testBuildMap() {
        String text = "this is a test yes this is a test.";
        EfficientMarkovModel markov = new EfficientMarkovModel(4);
        markov.setTraining(text);
        HashMap<String,ArrayList<String>> mappedChars = markov.buildMap();
        for (String key: mappedChars.keySet()) {
            System.out.println("Key: "+key+"\tValues: "+mappedChars.get(key));
        }
    }
}
