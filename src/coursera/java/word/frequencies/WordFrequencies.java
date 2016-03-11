package coursera.java.word.frequencies;
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 *
 * @author Duke Software Team
 * @version 1.0
 */

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        //first clear both myWords and myFreqs using .clear()
        myWords.clear();
        myFreqs.clear();
        //select a file
        FileResource resource = new FileResource();
        //iterate over every word of the file
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            //put unique words found into myWords and add an element to myFreqs
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            //or add 1 to myFreqs for non-unique words found
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        int index = findMax();
        System.out.println("max word/freq: " + myWords.get(index) + " " + myFreqs.get(index));
        //for (int k = 0; k < myWords.size(); k++) {
        //System.out.println("Word: "+myWords.get(k)+"\tNumber of times found: "+myFreqs.get(k));
        //}
    }

    public int findMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int k = 0; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) > max) {
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
