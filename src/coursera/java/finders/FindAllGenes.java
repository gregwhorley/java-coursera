/**
 * Find all genes in a DNA string...and a bunch of other stuff
 * 
 * @author greg 
 * @version 1.0
 */

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class FindAllGenes {
    public int findStopIndex(String dna, int index) {
        int stop1 = dna.indexOf("tag", index);
        if (stop1 == -1 || (stop1 - index) % 3 != 0) {
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("tga", index);
        if (stop2 == -1 || (stop2 - index) % 3 != 0) {
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("taa", index);
        if (stop3 == -1 || (stop3 - index) % 3 != 0) {
            stop3 = dna.length();
        }
        if (stop1 == dna.length() && stop2 == dna.length() && stop3 == dna.length()) {
            return -1;
        }
        else {
            return Math.min(stop1, Math.min(stop2, stop3));
        }
    }
    public void printAll(String dna) {
        System.out.println("DNA string is:");
        System.out.println(dna);
        System.out.println("Gene(s) found:");
        
        String dnaLower = dna.toLowerCase();
        int marker = 0;
        while (true) {
            int startCodon = dnaLower.indexOf("atg", marker);
            if (startCodon == -1) {
                break;
            }
            int stopCodon = findStopIndex(dnaLower,startCodon+3);
            if (stopCodon == -1) {
                marker = startCodon + 3;
            }
            else {
                System.out.println(dna.substring(startCodon,stopCodon+3));
                marker = stopCodon+3;
            }
        }
        
    }
    public StorageResource storeAll(String dna) {
        StorageResource store = new StorageResource();
        
        String dnaLower = dna.toLowerCase();
        int marker = 0;
        while (true) {
            int startCodon = dnaLower.indexOf("atg", marker);
            if (startCodon == -1) {
                break;
            }
            int stopCodon = findStopIndex(dnaLower,startCodon+3);
            if (stopCodon == -1) {
                marker = startCodon + 3;
            }
            else {
                store.add(dna.substring(startCodon,stopCodon+3));
                marker = stopCodon + 3;
            }
        }
        return store;
    }
    public float cgRatio(String dna) {
        String dnaLower = dna.toLowerCase();
        int marker = 0;
        int cgSum = 0;
        while (dnaLower.indexOf("c", marker) != -1) {
            //this stops at c and doesn't get the g totals
            //so maybe two loops are needed here, one for c and another for g
            cgSum = cgSum + 1;
            marker = (dnaLower.indexOf("c", marker) + 1);
        }
        marker = 0;
        while (dnaLower.indexOf("g", marker) != -1) {
            cgSum = cgSum + 1;
            marker = (dnaLower.indexOf("g", marker) + 1);
        }
        return (float)cgSum/dna.length();
    }
    public int ctgFinder(String dna) {
        String dnaLower = dna.toLowerCase();
        int marker = 0;
        int ctgSum = 0;
        while (dnaLower.indexOf("ctg",marker) != -1) {
            ctgSum = ctgSum + 1;
            marker = (dnaLower.indexOf("ctg",marker) + 3);
        }
        return ctgSum;
    }
    public int longestGene(StorageResource sr) {
        int longest = 0;
        for (String item : sr.data()) {
            if (item.length() > longest) {
                longest = item.length();
            }
        }
        return longest;
    }
    public void printGenes(StorageResource sr) {
        int overSixtyCount = 0;
        int cgRatioCount = 0;
        for (String item : sr.data()) {
            if (item.length() > 60) {
                //System.out.println(item);
                overSixtyCount = overSixtyCount + 1;
            }
            if (cgRatio(item) > 0.35) {
                //System.out.println(item);
                cgRatioCount = cgRatioCount + 1;
            }
        }
        System.out.println("Number of strings over 60 chars: " + overSixtyCount);
        System.out.println("Number of strings whose C-G ratio is higher than 0.35: " + cgRatioCount);
    }
    public void testFinder() {
        String dna1 = "ATGAAATGAAAA";
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        String dna4 = "ATGAATTAGTAACTGATGAATTAACTGAATTCTGGGGGGGCTGATGCTG";
        //printAll(dna1);
        //printAll(dna2);
        //printAll(dna3);
        System.out.println(dna4);
        System.out.println("Number of instances of CTG: " + ctgFinder(dna4));
    }
    public void testStorageFinder() {
        //FileResource file = new FileResource("brca1line.fa");
        FileResource file = new FileResource("GRch38dnapart.fa");
        StorageResource results = storeAll(file.asString());
        String ctgs = file.asString();
        System.out.println("Number of genes found: " + results.size());
        printGenes(results);
        System.out.println("Number of CTGs: " + ctgFinder(ctgs));
        System.out.println("Longest gene size: " + longestGene(results));
    }
}
