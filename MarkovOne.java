
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length()) {
            int foundKey = myText.indexOf(key,placeHolder);
            if (foundKey != -1 && foundKey < myText.length()-key.length()) {
                follows.add(myText.substring(foundKey+key.length(),
                                                foundKey+key.length()+1));
                placeHolder = foundKey + key.length();
            }
            else {
                placeHolder++;
            }
        }
        return follows;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s;
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-2);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
}
