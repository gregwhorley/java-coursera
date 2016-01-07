
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovFour implements IMarkovModel {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length()) {
            int foundKey = myText.indexOf(key,placeHolder);
            /*
            if (foundKey != -1 && foundKey < myText.length()-key.length()-1) {
                follows.add(myText.substring(foundKey+key.length(),
                                                foundKey+key.length()+1));
                placeHolder = foundKey + key.length();
            }
            else {
                placeHolder++;
            }
            */
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
        int index = myRandom.nextInt(myText.length()-5);
        String key = myText.substring(index, index+4);
        sb.append(key);
        
        for(int k=0; k < numChars-4; k++){
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