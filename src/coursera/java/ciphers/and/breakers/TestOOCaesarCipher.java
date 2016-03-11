
/**
 * Write a description of TestOOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class TestOOCaesarCipher {
    public int[] countOccurrencesOfLetters(String message) {
        //snippet from lecture
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values) {
        int maxDex = 0;
        for (int k=0; k < values.length; k++) {
            if (values[k] > values[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    public void simpleTests() {
        FileResource fileResource = new FileResource();
        String fileAsString = fileResource.asString();
        OOCaesarCipher oocc = new OOCaesarCipher(18);
        String encrypted = oocc.encrypt(fileAsString);
        System.out.println("Encrypted string:\n"+encrypted);
        String decrypted = oocc.decrypt(encrypted);
        System.out.println("Decrypted string:\n"+decrypted);
        
        String blindDecrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted string using breakCaesarCipher():\n"+blindDecrypted);
    }
    public String breakCaesarCipher(String input) {
        int[] freqs = countOccurrencesOfLetters(input);
        int freqDex = maxIndex(freqs);
        int dkey = freqDex - 4;
        if (freqDex < 4) {
            dkey = 26 - (4-freqDex);
        }
        OOCaesarCipher oocc = new OOCaesarCipher(dkey);
        return oocc.decrypt(input);
    }
}
