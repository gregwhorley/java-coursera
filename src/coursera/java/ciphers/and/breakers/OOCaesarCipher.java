
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class OOCaesarCipher {
    private String alphabetLower;
    private String alphabetUpper;
    private String shiftedAlphabetLower;
    private String shiftedAlphabetUpper;
    private int mainKey;
    public OOCaesarCipher(int key) {
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
        shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int index=0; index < encrypted.length(); index++) {
            Character currChar = encrypted.charAt(index);
            int currentIndex = 0;
            
            if (Character.isLowerCase(currChar)) {
                currentIndex = alphabetLower.indexOf(currChar);
            }
            else {
                currentIndex = alphabetUpper.indexOf(currChar);
            }
            
            if (currentIndex != -1 && Character.isLowerCase(currChar)) {
                char newChar = shiftedAlphabetLower.charAt(currentIndex);
                encrypted.setCharAt(index, newChar);
            }
            else if (currentIndex != -1) {
                char newChar = shiftedAlphabetUpper.charAt(currentIndex);
                encrypted.setCharAt(index, newChar);
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input) {
        OOCaesarCipher oocc = new OOCaesarCipher(26-mainKey);
        return oocc.encrypt(input);
    }
}
