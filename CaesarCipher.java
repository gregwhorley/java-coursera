import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key)+alphabetLower.substring(0,key);
        //Count from 0 to < length of encrypted, (call it index)
        for(int index = 0; index < encrypted.length(); index++) {
            //Look at the ith character of encrypted (call it currChar)
            Character currChar = encrypted.charAt(index);
            //Find the index of currChar in the alphabet (call it currIdx)
            int currIdx = 0;
            if (Character.isLowerCase(currChar)) {
                currIdx = alphabetLower.indexOf(currChar);
            }
            else {
                currIdx = alphabetUpper.indexOf(currChar);
            }
            //If currChar is in the alphabet 
            if(currIdx != -1 && Character.isLowerCase(currChar)) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabetLower.charAt(currIdx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(index, newChar);
            }
            else if (currIdx != -1) {
                char newChar = shiftedAlphabetUpper.charAt(currIdx);
                encrypted.setCharAt(index, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        //Returns a String that has been encrypted using the following algorithm.
        //Parameter key1 is used to encrypt every other character with the Caesar Cipher 
        //algorithm, starting with the first character, and key2 is used to encrypt every 
        //other character, starting with the second character. 
        //For example, the call encryptTwoKeys(“First Legion”, 23, 17) should return
        //“Czojq Ivdzle”. Note the ‘F’ is encrypted with key 23, the first ‘i’ with 17, 
        //the ‘r’ with 23, and the ‘s’ with 17, etc.
        StringBuilder encryptedInput = new StringBuilder(input);
        for (int index=0; index < input.length(); index++) {
            if (index % 2 == 0 || index == 0) {
                encryptedInput.replace(index,index+1,encrypt(input.substring(index,index+1),key1));
            }
            else {
                encryptedInput.replace(index,index+1,encrypt(input.substring(index,index+1),key2));
            }
        }
        return encryptedInput.toString();
    }
    public void testCaesar() {
        int key1 = 8;
        int key2 = 21;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}