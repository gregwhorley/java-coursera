package coursera.java.word.frequencies;
/**
 * Write a description of CharactersInPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

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
        } else {
            int freq = myCharacterFreqs.get(index);
            myCharacterFreqs.set(index, freq + 1);
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
            int periodInLine = line.indexOf(".");
            if (periodInLine != -1) {
                //extract the possible name of the speaking part
                String possibleName = line.substring(0, periodInLine);
                //and call update() to count it as an occurrence for this person
                update(possibleName);
            }
        }
    }

    public int findMax() {
        int maxElement = myCharacterFreqs.get(0);
        int maxIndex = 0;
        for (int k = 0; k < myCharacterFreqs.size(); k++) {
            if (myCharacterFreqs.get(k) > maxElement) {
                maxElement = myCharacterFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void tester() {
        //call findAllCharacters()
        findAllCharacters();
        //test charactersWithNumParts()
        charactersWithNumParts(2, 999);
        //print out each main character, followed by number of speaking parts for that character
        //"A main character is one who has more speaking parts than most people"
        int bigIndex = findMax();
        System.out.println("Character with most speaking parts: " + myCharacters.get(bigIndex)
                + "\t" + myCharacterFreqs.get(bigIndex));
        //for (int index=0;index < myCharacters.size();index++) {
        //System.out.println(myCharacters.get(index)+"\t"+myCharacterFreqs.get(index));
        //}
    }

    public void charactersWithNumParts(int num1, int num2) {
        //assume num1 is less than or equal to num2

        //This method should print out the names of all those characters that have
        // exactly number speaking parts, where number is greater than or equal to
        // num1 and less than or equal to num2
        for (int k = 0; k < myCharacterFreqs.size(); k++) {
            if (myCharacterFreqs.get(k) >= num1 && myCharacterFreqs.get(k) <= num2) {
                System.out.println(myCharacters.get(k) + "\t\t" + myCharacterFreqs.get(k));
            }
        }
    }
}
