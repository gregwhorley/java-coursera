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
            String slicedString = sliceString(encrypted,index,klength);
            key[index] = cracker.getKey(slicedString);
        }
        return key;
    }

    public void breakVigenere () {
        //This method should perform 6 tasks (in this order)
        //1. Create a new FileResource using its default constructor
        //2. Use the asString method to read contents as String
        //3. Use the tryKeyLength() method to find the key for the message you read in
        //4. Create a new VigenereCipher, passing in the key that tryKeyLength found
        //5. Use VigenereCipher's decrypt method to decrypt the message
        //6. print out the decryped message
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        //hardcoded key length and most common char for now
        int[] decryptKeys = tryKeyLength(encrypted,4,'e');
        VigenereCipher vigCipher = new VigenereCipher(decryptKeys);
        String message = vigCipher.decrypt(encrypted);
        System.out.println(message);
        for (int index=0;index < decryptKeys.length;index++) {
            System.out.println(decryptKeys[index]);
        }
    }
    
}
