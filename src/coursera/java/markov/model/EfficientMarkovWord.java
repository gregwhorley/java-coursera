
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

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
    }
    
    public void printHashMapInfo() {
        int largest = 0;
        //print the hashmap
        for (WordGram wordGram : myMap.keySet()) {
            //System.out.println("Key: "+wordGram+"\tValue: "+myMap.get(wordGram));
            if (myMap.get(wordGram).size() > largest) {
                largest = myMap.get(wordGram).size();
            }
        }
        //print the number of keys in the hashmap
        System.out.println("Number of keys in hashmap: "+myMap.size());
        //print the size of the largest value in the hashmap
        System.out.println("Size of largest value in hashmap: "+largest);
        //print the keys that have the maximum value
        System.out.println("Keys that have maximum value: ");
        for (WordGram wordGram : myMap.keySet()) {
            if (myMap.get(wordGram).size() == largest) {
                System.out.println(wordGram.toString());
            }
        }
    }
    
    private HashMap<WordGram,ArrayList<String>> buildMap() {
        HashMap<WordGram,ArrayList<String>> mappedWords = new HashMap<WordGram,
                    ArrayList<String>>();
        int counter = 0;
        //while the current location of the myText array is less than the array's length minus the order
        while (counter < myText.length-(myOrder-1)) {
            //create a new wordgram object that starts at the current location of myText string array
            //  with a length of myOrder
            WordGram wordGram = new WordGram(myText,counter,myOrder);
            
            //if the wordgram string array, acting as the key in the hashmap, is not in the hashmap
            if (!mappedWords.containsKey(wordGram) && counter+myOrder < myText.length) {
                //add new entry in hashmap with key of wordgram and value of word that follows
                mappedWords.put(wordGram, new ArrayList<String>(Arrays.asList(myText[counter+myOrder])));
                //System.out.println("Word that follows wordgram: "+myText[counter+myOrder]);
            }
            //if the wordgram string array is already in the hashmap
            else if (mappedWords.containsKey(wordGram) && counter+myOrder < myText.length) {
                //get entry and replace it with current value + value of word that follows
                ArrayList<String> currentValues = mappedWords.get(wordGram);
                currentValues.add(myText[counter+myOrder]);
                mappedWords.replace(wordGram,currentValues);
                //System.out.println("Words in wordgram: "+currentValues);
            }
            //if the wordgram string array is not in the hashmap and we're at the end of the myText array
            else if (!mappedWords.containsKey(wordGram) && counter + myOrder == myText.length) {
                //create new entry with key of wordgram and empty value set
                mappedWords.put(wordGram, new ArrayList<String>());
            }
            counter++;
        }
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
        return myMap.get(kGram);
    }
    
    public String getRandomText(int numWords){
        myMap = buildMap();
        printHashMapInfo();
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
