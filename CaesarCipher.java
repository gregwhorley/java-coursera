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
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "A BAT";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}