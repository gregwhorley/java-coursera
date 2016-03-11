
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String,ArrayList<String>> markovMap;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        order = n;
    }
    
    public void setTraining(String s){
        myText = s;
        markovMap = buildMap();
    }

    private HashMap<String,ArrayList<String>> buildMap() {
        HashMap<String,ArrayList<String>> mappedChars = new HashMap<String,ArrayList<String>>();
        int placeHolder = 0;
        while (placeHolder < myText.length()-(order-1)) {
            String key = myText.substring(placeHolder,placeHolder+order);
            if (!mappedChars.containsKey(key) && placeHolder + order < myText.length()) {
                mappedChars.put(key,new ArrayList<String>(Arrays.asList(
                          myText.substring(placeHolder+key.length(),placeHolder+key.length()+1))));
            }
            else if (mappedChars.containsKey(key) && placeHolder + order < myText.length()){
                ArrayList<String> currentValues = mappedChars.get(key);
                currentValues.add(myText.substring(placeHolder+key.length(),placeHolder+key.length()+1));
                mappedChars.replace(key,currentValues);
            }
            else if (placeHolder + order == myText.length()){
                mappedChars.put(key, new ArrayList<String>());
            }
            
            placeHolder++;
        }
        
        return mappedChars;
    }
    
    @Override
    public ArrayList<String> getFollows(String key) {
        return markovMap.get(key);
    }
    
    public void printHashMapInfo() {
        System.out.println("Key total: "+markovMap.size());
        int largest = 0;
        int counter = 0;
        for (String key: markovMap.keySet()) {
            //System.out.println("Key number: "+counter+"\tKey text: "+key+"\tKey value: "+markovMap.get(key));
            if (markovMap.get(key).size() > largest) {
                largest = markovMap.get(key).size();
            }
            counter++;
        }
        System.out.println("Largest value in HashMap: "+largest);
        ArrayList<String> keysWithMax = new ArrayList<String>();
        for (String key: markovMap.keySet()) {
            if (markovMap.get(key).size() == largest) {
                keysWithMax.add(key);
            }
        }
        System.out.println("Keys with maximum size value: "+keysWithMax);
    }
    
    public String getRandomText(int numChars){
        printHashMapInfo();
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