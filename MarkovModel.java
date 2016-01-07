
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    int keyLength;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        keyLength = n;
    }
    
    public ArrayList<String> getFollows(String key) {
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
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s;
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-(keyLength+1));
        String key = myText.substring(index, index+keyLength);
        sb.append(key);
        
        for(int k=0; k < numChars-keyLength; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key "+key+" "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
}