import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for (int index=whichSlice;index < message.length();index+=totalSlices) {
            sliced.append(message.charAt(index));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        
        for (int index=0; index < key.length; index++) {
            key[index] = cracker.getKey(sliceString(encrypted,index,klength));
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fileResource) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fileResource.lines()) {
            line = line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }
    public int countWords(String message, HashSet<String> dictionary) {
        int wordCount = 0;
        List<String> splitMessage = new ArrayList<String>(Arrays.asList(message.split("\\W")));
        for (int index=0;index < splitMessage.size();index++) {
            if (dictionary.contains(splitMessage.get(index).toLowerCase())) {
                wordCount++;
            }
        }
        return wordCount;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decrypted = "";
        int highestWordCount = 0;
        int correctKeyLength = 0;
        for (int index=1; index < 100; index++) {
            int[] currentKeys = tryKeyLength(encrypted, index, 'e');
            VigenereCipher vigCipher = new VigenereCipher(currentKeys);
            String message = vigCipher.decrypt(encrypted);
            int currentWordCount = countWords(message, dictionary);
            if (currentWordCount > highestWordCount) {
                highestWordCount = currentWordCount;
                correctKeyLength = index;
                decrypted = message;
            }
        }
        System.out.println("Key length: "+correctKeyLength);
        System.out.println("Word count: "+highestWordCount);
        return decrypted;
    }
    
    public void breakVigenere () {
        /**
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        //hardcoded key length and most common char for now
        int[] decryptKeys = tryKeyLength(encrypted,38,'e');
        VigenereCipher vigCipher = new VigenereCipher(decryptKeys);
        String message = vigCipher.decrypt(encrypted);
        //System.out.println(message);
        //for (int index=0;index < decryptKeys.length;index++) {
        //    System.out.println(decryptKeys[index]);
        //}
        FileResource dictionaryFile = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        int wordCount = countWords(message, dictionary);
        System.out.println("Word count: "+wordCount);
        */
       
        /**
         * 1. Create a new FileResource using its default constructor 
         *    (which displays a dialog for you to select a file to decrypt).
         * 2. Use that FileResource’s asString method to read the entire contents
         *     of the file into a String.
         * 3. You should make a new FileResource to read from the English dictionary
         *     file that we have provided.
         * 4. You should use your readDictionary method to read the contents of that 
         *     file into a HashSet of Strings.
         * 5. You should use the breakForLanguage method that you just wrote to decrypt
         *     the encrypted message.
         * 6. Finally, you should print out the decrypted message!
         * 
         * 1. 57
         * 2. 31565
         * 3. The Tragedy of Hamlet, Prince of Denmark
         * 4. 4324
         */
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        FileResource dictionaryFile = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
        
    }
    
}
