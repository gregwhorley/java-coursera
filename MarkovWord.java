
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    //System.out.println("Key: "+key+"\tValue: "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		return sb.toString().trim();
	}
	
	private int indexOf(String[] words, WordGram target, int start) {
	    for (int index=start;index<words.length-target.length();index++) {
	        if (words[index].equals(target.wordAt(0))) {
	            boolean targetFound = true;
	            for (int k=1;k<target.length();k++) {
	                if (!words[k].equals(target.wordAt(k))) {
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
	
	public void testIndexOf() {
	    String text = "this is just a test yes this is a simple test";
	    String[] textArray = text.split("\\s");
	    String[] targetArray = "this is".split("\\s");
	    WordGram target = new WordGram(targetArray,0,targetArray.length);
	    System.out.println(indexOf(textArray,target,0));
	    System.out.println(indexOf(textArray,target,5));
	    System.out.println(indexOf(textArray,target,8));
	    
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length) {
            //int foundKey = indexOf(myText, key, placeHolder);
           int foundKey = 0;
           if (foundKey == -1) {
               break;
            }
           if (foundKey+key.length() >= myText.length-1) {
               break;
            }
           String next = myText[foundKey+1];
           follows.add(next);
           placeHolder = foundKey + 1;
        }
        return follows;
    }

}
