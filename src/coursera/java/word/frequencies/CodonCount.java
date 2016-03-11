package coursera.java.word.frequencies;
/**
 * Write a description of CodonCount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna) {
        //clear out map before building
        codonMap.clear();
        //This method will build a new map of codons mapped to 
        //their counts from the string dna with the reading frame
        //with the position start (a value of 0, 1, or 2).
        for (int index = start; dna.length() - index > 3; index += 3) {
            String currentCodon = dna.substring(index, index + 3);
            if (!codonMap.containsKey(currentCodon)) {
                codonMap.put(currentCodon, 1);
            } else {
                codonMap.put(currentCodon, codonMap.get(currentCodon) + 1);
            }
        }
    }

    private String getMostCommonCodon() {
        //get the codon in a reading frame that has the largest count
        //this method assumes the HashMap of codons to counts has already been built
        int currentHigh = 0;
        String mostCommonCodon = "";
        for (String s : codonMap.keySet()) {
            int currentCount = codonMap.get(s);
            if (currentCount > currentHigh) {
                mostCommonCodon = s;
                currentHigh = currentCount;
            }
        }
        return mostCommonCodon;
    }

    private void printCodonCounts(int start, int end) {
        //This method prints all the codons in the HashMap along with their
        //counts if their count is between start and end, inclusive.
        for (String s : codonMap.keySet()) {
            if (codonMap.get(s) >= start && codonMap.get(s) <= end) {
                System.out.println(s + "\t" + codonMap.get(s));
            }
        }
    }

    public void testBuildCodonMap() {
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString();
        dna = dna.toUpperCase();
        for (int index = 0; index <= 2; index++) {
            System.out.println("\nTesting with start position " + index + ":\n");
            buildCodonMap(index, dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Total unique codons found: " + codonMap.size());
            System.out.println("\nMost common codon: " + mostCommonCodon
                    + "\t" + codonMap.get(mostCommonCodon));
            printCodonCounts(4, 8);
        }
    }
}
