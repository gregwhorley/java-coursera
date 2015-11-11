
/**
 * 
 * Find all genes in a DNA string.
 * 
 * @author greg 
 * @version 1.0
 */
public class FindAllGenes {
    public int findStopIndex(String dna, int index) {
        //find the first occurrence of each stop codon to the right of index
        //from those stop codons that are a multiple of three from index, return the smallest index position
        //return -1 if no stop codon was found
        
        // 3. If the length of the substring between ATG and any of these three stop codons is a multiple of three, then a candidate for a gene
        //    is the start codon through the end of the stop codon
        // 4. If there is more than one valid candidate, the smallest such string is the gene. The gene includes the start and stop codon
        while (true) {
            if (dna.contains("tag") && dna.indexOf("tag",index) % 3 == 0) {
                //do something
            }
            else if (dna.contains("tga") && dna.indexOf("tga",index) % 3 == 0) {
                //do something
            }
            else if (dna.contains("taa") && dna.indexOf("taa",index) % 3 == 0) {
                //do something
            }
            else {
                return -1;
            }
        }
    }
    public void printAll(String dna) {
        //this method should print all the genes it finds in DNA
        //look repeatedly for a gene; if a gene is found, then print it and continue looking for another gene
        //this method should call findStopIndex
        
        // 5. If no start codon was found, then you are done.
        String dnaLower = dna.toLowerCase();
        if (dnaLower.indexOf("atg") == -1) {
            System.out.println("Start codon not found! Exiting");
            System.exit(0);
        }
        // 1. to find the first gene, find the start codon ATG
        int startCodon = dnaLower.indexOf("atg");
        // 2. Next look immediately past ATG for the first occurrence of each of the three stop codons TAG, TGA, and TAA
        int stopCodon = findStopIndex(dnaLower, startCodon);
        // 6. If a start codon was found, but no gene was found, then start searching for another gene via the next occurrence of a start
        //    codon starting immediately after the start codon that didn't yield a gene.
        
    }
    public void testFinder() {
        //test method that prints full DNA strings set within this method and prints genes found in the strings
    }
}
