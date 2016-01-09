
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    int order;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        order = n;
    }
    
    public void setTraining(String s){
        myText = s;
    }
    /*
     * Write a new class named EfficientMarkovModel (make a copy of MarkovModel to start with) 
     * that extends AbstractMarkovModel and that builds a HashMap to calculate the follows 
     * ArrayList for each possible substring only once, and then uses the HashMap to look at 
     * the list of characters following when it is needed.
     */
    public void buildMap(HashMap<String,ArrayList<String>> map) {
        /*
         * Build the HashMap (Be sure to handle the case where there may not be a 
         * follow character. If that key is not in the HashMap yet, then it should 
         * be put in mapped to an empty ArrayList.) Think carefully about where to 
         * call this method, considering that you will want to build a map for each 
         * new training text. 
         * 
         * 
         * The map should be built after only one scan of the training text
         * 
         */
        
        
    }
    
    public ArrayList<String> getFollows(String key) {
        /*
         *  This getFollows method should be much shorter, as it can look up the 
         *  ArrayList of Strings, instead of computing it each time.
         *  
         *  
         */
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-(order+1));
        String key = myText.substring(index, index+order);
        sb.append(key);
        
        for(int k=0; k < numChars-order; k++){
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
    
    public String toString() {
        return "EfficientMarkovModel of order "+order;
    }
}