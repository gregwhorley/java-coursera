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
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message,15);
        System.out.println("Answer to 1:\n" + encrypted);
        
        String encryptedTwo = encryptTwoKeys(message, 21, 8);
        System.out.println("Answer to 2:\n" + encryptedTwo);
        
        String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 14;
        int key2 = 24;
        System.out.println("Answer to 6:\n" + encryptTwoKeys(input,26-key1,26-key2));
        
        //String message = "Top ncmy qkff vi vguv vbg ycpx";
        //String encrypted = encryptTwoKeys(message, key1, key2);
        //System.out.println(encrypted);
        
        //String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        //String decrypted = encryptTwoKeys(message,26-key1,26-key2);
        //System.out.println(decrypted);
        
        
    }
}