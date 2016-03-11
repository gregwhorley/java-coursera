
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource fileResource, int[] counts) {
        //This method should read in the words from fileResource and count the number
        //of words of each length for all the words in resource, storing these 
        //counts in the array counts.
        for (String word : fileResource.words()) {
            word = word.toLowerCase();
            int letterCount = 0;
            for (int index=0; index < word.length(); index++) {
                if ((index == 0 || index == (word.length()-1)) && 
                    !Character.isLetter(word.charAt(index))) {
                    continue;
                }
                letterCount++;
            }
            if (letterCount >= counts.length) {
                counts[counts.length] += 1;
            }
            else {
                counts[letterCount] += 1;
            }
        }
    }
    public void testCountWordLengths() {
        FileResource fileResource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fileResource, counts);
        for (int k=1; k < counts.length; k++) {
            System.out.println("Number of " + k + "-letter words\t" + counts[k]);
        }
        System.out.println("Most common word length is " + indexOfMax(counts) +
                                " letters");
    }
    public int indexOfMax(int[] values) {
        //This method returns the index position of the largest element in values.
        //Then add code to the method testCountWordLengths to call indexOfMax to 
        //determine the most common word length in the file. For example, calling
        //indexOfMax after calling countWordLengths on the file smallHamlet.txt 
        //should return 3.
        int indexOfMax = 0;
        for (int index = 0; index < values.length; index++) {
            if (values[index] > values[indexOfMax]) {
                indexOfMax = index;
            }
        }
        return indexOfMax;
    }
}
