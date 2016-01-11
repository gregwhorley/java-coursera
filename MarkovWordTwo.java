
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String twoWordKey = myText[index]+" "+myText[index+1];
		sb.append(twoWordKey);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(twoWordKey);
		    System.out.println("Key: "+twoWordKey+"\tValue: "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			twoWordKey = twoWordKey.substring(1)+" "+next;
		}
		
		return sb.toString().trim();
	}
	
	private int indexOf(String[] words, String target, int start) {
	    for (int index=start;index<words.length;index++) {
	        if (words[index].equals(target)) {
	            return index;
	        }
	    }
	    return -1;
	}
	
	public void testIndexOf() {
	    String text = "this is just a test yes this is a simple test";
	    String[] textArray = text.split("\\s");
	    int indexOfThis = indexOf(textArray,"this",0);
	    System.out.println(indexOfThis);
	    indexOfThis = indexOf(textArray,"this",3);
	    System.out.println(indexOfThis);
	    int indexOfFrog = indexOf(textArray,"frog",0);
	    System.out.println(indexOfFrog);
	    indexOfFrog = indexOf(textArray,"frog",5);
	    System.out.println(indexOfFrog);
	    int indexOfSimple = indexOf(textArray,"simple",2);
	    System.out.println(indexOfSimple);
	    int indexOfTest = indexOf(textArray,"test",5);
	    System.out.println(indexOfTest);
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
        int placeHolder = 0;
        while (placeHolder < myText.length) {
            int foundKey = indexOf(myText, key, placeHolder);
           if (foundKey == -1) {
               break;
            }
           if (foundKey+key.length() >= myText.length) {
               break;
            }
           String next = myText[foundKey+1];
           follows.add(next);
           //placeHolder = foundKey + key.length();
           placeHolder = foundKey + 1;
        }
        return follows;
    }

}
