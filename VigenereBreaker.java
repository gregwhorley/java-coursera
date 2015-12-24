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
            char commonChar = mostCommonCharIn(dictionary);
            int[] currentKeys = tryKeyLength(encrypted, index, commonChar);
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
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        /**
         * This method should find out which character, of the letters in the English
         * alphabet, appears most often in the words in dictionary. It should return this 
         * most commonly occurring character. Remember that you can iterate over a HashSet 
         * of Strings with a for-each style for loop.
         */
        HashMap<Character,Integer> characterCounter = new HashMap<Character,Integer>();
        for (String word : dictionary) {
            char[] letters = word.toCharArray();
            for (int index=0;index<letters.length;index++) {
                if (!characterCounter.containsKey(letters[index])) {
                    characterCounter.put(letters[index],1);
                }
                else {
                    characterCounter.replace(letters[index],characterCounter.get(letters[index])+1);
                }
            }
        }
        int highestCount = 0;
        char mostUsedChar = '\0';
        for (Character character : characterCounter.keySet()) {
            if (characterCounter.get(character) > highestCount) {
                highestCount = characterCounter.get(character);
                mostUsedChar = character;
            }
        }
        return mostUsedChar;
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String,HashSet<String>> languages) {
        /**
         * Try breaking the encryption for each language, and see which gives the best results!
         * Remember that you can iterate over the language.keySet() to get the name of each 
         * language, and then you can use .get() to look up the corresponding dictionary for 
         * that language. You will want to use the breakForLanguage and countWords methods that
         * you already wrote to do most of the work (it is slightly inefficient to re-count the
         * words here, but it is simpler, and the inefficiency is not significant). You will 
         * want to print out the decrypted message as well as the language that you identified
         * for the message.
         */
        int currentHigh = 0;
        String decryptedMessage = "";
        String usedLanguage = "";
        for (String currentLanguage : languages.keySet()) {
            String message = breakForLanguage(encrypted,languages.get(currentLanguage));
            int currentWordCount = countWords(message,languages.get(currentLanguage));
            if (currentWordCount > currentHigh) {
                decryptedMessage = message;
                currentHigh = currentWordCount;
                usedLanguage = currentLanguage;
            }
        }
        System.out.println(decryptedMessage);
        System.out.println("Language: "+usedLanguage);
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
         * 
         *
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        FileResource dictionaryFile = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
        */
       
       /**
        * Modify your breakVigenere method to read many dictionaries instead of just one.
        * In particular, you should make a HashMap mapping Strings to a HashSet of Strings
        * that will map each language name to the set of words in its dictionary. Then, you
        * will want to read (using your readDictionary method) each of the dictionaries that
        * we have provided (Danish, Dutch, English, French, German, Italian, Portuguese, and
        * Spanish) and store the words in the HashMap you made. Reading all the dictionaries
        * may take a little while, so you might add some print statements to reassure you that
        * your program is making progress. Once you have made that change, you will want to 
        * call breakForAllLangs, passing in the message (the code to read in the message is 
        * unchanged from before), and the HashMap you just created.
        */
    }
    
}
