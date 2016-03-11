
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key1,key2);
		    System.out.println("Keys: "+key1+" "+key2+"\tValue: "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		return sb.toString().trim();
	}
	
	private int indexOf(String[] words, String target1, String target2, int start) {
	    for (int index=start;index<words.length-1;index++) {
	        if (words[index].equals(target1) && words[index+1].equals(target2)) {
	            return index;
	        }
	    }
	    return -1;
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length) {
           int foundKey = indexOf(myText, key1, key2, placeHolder);
           if (foundKey == -1) {
               break;
            }
           if (foundKey+2 >= myText.length) {
               break;
            }
           String next = myText[foundKey+2];
           follows.add(next);
           placeHolder = foundKey + 2;
        }
        return follows;
    }

}
