
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myCharacterFreqs;
    
    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myCharacterFreqs = new ArrayList<Integer>();
    }
    public void update(String person) {
        //update ArrayLists by either adding new characters
        //or increasing frequency of existing characters
        person = person.toLowerCase();
        int index = myCharacters.indexOf(person);
        if (index == -1) {
            myCharacters.add(person);
            myCharacterFreqs.add(1);
        }
        else {
            int freq = myCharacterFreqs.get(index);
            myCharacterFreqs.set(index,freq+1);
        }
    }
    public void findAllCharacters() {
        //clear ArrayLists first
        myCharacters.clear();
        myCharacterFreqs.clear();
        //open a file for reading line-by-line
        FileResource fileResource = new FileResource();
        //for each line
        for (String line : fileResource.lines()) {
            //if a period is found
            if (line.indexOf(".") != -1) {
                //extract the possible name of the speaking part and call update()
                // to count it as an occurrence for this person
                update(line);
            }
        }
    }
    
    public void tester() {
        //call findAllCharacters()
        findAllCharacters();
        //print out each main character, followed by number of speaking parts for that character
        //"A main character is one who has more speaking parts than most people" (probably need a findMax() method)
        for (int index=0;index < myCharacters.size();index++) {
            System.out.println(myCharacters.get(index)+"\t"+myCharacterFreqs.get(index));
        }
    }
    public void charactersWithNumParts(int num1, int num2) {
        //assume num1 is less than or equal to num2
        
        //This method should print out the names of all those characters that have
        // exactly number speaking parts, where number is greater than or equal to
        // num1 and less than or equal to num2
        
    }
}
