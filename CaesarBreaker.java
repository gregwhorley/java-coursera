
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
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
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countOccurrencesOfLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int index=start;index < message.length();index += 2) {
            halfString.append(message.charAt(index));
        }
        return halfString.toString();
    }
    public int getKey(String s) {
        int[] letterFreqs = countOccurrencesOfLetters(s);
        return maxIndex(letterFreqs);
    }
    public String decryptTwoKeys(String encrypted) {
        String firstHalfEncrypted = halfOfString(encrypted,0);
        String secondHalfEncrypted = halfOfString(encrypted,1);
        int firstHalfKey = getKey(firstHalfEncrypted);
        int secondHalfKey = getKey(secondHalfEncrypted);
        CaesarCipher cc = new CaesarCipher();
        int dkey1 = firstHalfKey - 4;
        int dkey2 = secondHalfKey - 4;
        if (firstHalfKey < 4) {
            dkey1 = 26 - (4-firstHalfKey);
        }
        if (secondHalfKey < 4) {
            dkey2 = 26 - (4-secondHalfKey);
        }
        System.out.println("First key:\t" + dkey1 + "\nSecond key:\t"
                            + dkey2);
        return cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
    }
    public void testDecrypt() {
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(encrypted));
    }
}
