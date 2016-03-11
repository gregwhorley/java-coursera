/**
 *Identify the first occurrence of the stop codon TAG after the start codon. If the length
 * of the substring between start and stop is a multiple of three, it's a gene
 * 
 *If no gene is found yet, then identify the first occurrence of the stop codon TGA after the
 * start codon. If the length of the substring between the start and stop codon is a multiple
 * of three, the gene is the string from the start codon to the TGA stop codon      
 * 
 *If no gene is found yet, do the same for stop codon TAA
 *@author Duke Software Team 
*/

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

public class TagFinderTwo {
    public String findProtein(String dna) {
        String dnaLower = dna.toLowerCase();
        //Identify the first start codon ATG in the string
        int start = dnaLower.indexOf("atg");
        if (start == -1) {
            return "";
        }
        if ((dnaLower.indexOf("tag", start+3) - start) % 3 == 0) {
            return dnaLower.substring(start, (dnaLower.indexOf("tag", start+3) + 3));
        }
        else if ((dnaLower.indexOf("tga", start+3) - start) % 3 == 0) {
            return dnaLower.substring(start, (dnaLower.indexOf("tga", start+3) + 3));
        }
        else if ((dnaLower.indexOf("taa", start+3) - start) % 3 == 0) {
            return dnaLower.substring(start, (dnaLower.indexOf("taa", start+3) + 3));
        }
        else {
            return "";
        }
    }
    public String stopCodon(String gene) {
        if ( gene.length() % 3 == 0 && gene.length() != 0) {
            return gene.substring(gene.length() - 3, gene.length());
        }
        else {
            return "";
        }
    }
    public void testing() {
        //String a = "acatgataacctaag";
        //String ap = "";
        String a = "ataaactatgttttaaatgt";
        String ap = "atgttttaa";
        //String a = "AATGCTAGTTTAAATCTGA";
        //String ap = "ATGCTAGTTTAAATCTGA".toLowerCase();
        //String a = "cccatggggtttaaataataataggagagagagagagagttt";
        //String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
            System.out.println("stop codon is " + stopCodon(result));
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}