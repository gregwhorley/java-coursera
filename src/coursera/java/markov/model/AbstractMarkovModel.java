
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length()) {
            int foundKey = myText.indexOf(key,placeHolder);
           if (foundKey == -1) {
               break;
            }
           if (foundKey+key.length() >= myText.length()) {
               break;
            }
           follows.add(myText.substring(foundKey+key.length(),foundKey+key.length()+1));
           placeHolder = foundKey + key.length();
        }
        return follows;
    }
}
