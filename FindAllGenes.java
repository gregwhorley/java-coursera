
/**
 * 
 * Find all genes in a DNA string.
 * 
 * @author greg 
 * @version 1.0
 */
public class FindAllGenes {
    public int findStopIndex(String dna, int index) {
        //Write the method findStopIndex that has two parameters dna and index, where dna is a String of 
        //DNA and index is a position in the string. This method finds the first occurrence of each stop 
        //codon to the right of index. From those stop codons that are a multiple of three from index, it 
        //returns the smallest index position. It should return -1 if no stop codon was found and there 
        //is no such position. This method was discussed in one of the videos.
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
        //this method should print all the genes it finds in DNA
        //look repeatedly for a gene; if a gene is found, then print it and continue looking for another gene
        //this method should call findStopIndex
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
    public void testFinder() {
        //test method that prints full DNA strings set within this method and prints genes found in the strings
        String dna1 = "ATGAAATGAAAA";
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        printAll(dna1);
        printAll(dna2);
        printAll(dna3);
    }
}
