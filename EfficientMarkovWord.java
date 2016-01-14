
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> myMap;
    private String[] myText;
    private Random myRandom;
    
    public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        myMap = buildMap();
    }
    
    private HashMap<WordGram,ArrayList<String>> buildMap() {
        /*
         * a method named buildMap to build the HashMap (Be sure to handle the 
         * case at the end where there is not a follow character. If that WordGram
         * is not in the HashMap yet, then it should be put in mapped to an empty
         * ArrayList. If that key is already in the HashMap, then do not enter
         * anything for this case.)
         */
        HashMap<WordGram,ArrayList<String>> mappedWords = new HashMap<WordGram,
                    ArrayList<String>>();
        return mappedWords;
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int index=start;index<words.length-target.length();index++) {
            if (words[index].equals(target.wordAt(0))) {
                boolean targetFound = true;
                for (int k=1;k<target.length();k++) {
                    if (!words[index+k].equals(target.wordAt(k))) {
                        targetFound = false;
                        break;
                    }
                }
                if (targetFound) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        /*
         * this getFollows method should be much shorter, as it can look up
         * the WordGram, instead of computing it each time.
         */
        ArrayList<String> follows = new ArrayList<String>();
        int counter = 0;
        while (counter < myText.length-kGram.length()) {
           int foundKey = indexOf(myText, kGram, counter);
           if (foundKey == -1) {
               break;
            }
           if (foundKey+kGram.length() >= myText.length-1) {
               break;
            }
           String next = myText[foundKey+kGram.length()];
           follows.add(next);
           counter = foundKey + kGram.length();
        }
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        sb.append(kGram.toString()).append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
