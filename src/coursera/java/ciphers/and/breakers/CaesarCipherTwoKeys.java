
/**
 * Write a description of CaesarCipherTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CaesarCipherTwoKeys {
    private String alphabetLower;
    private String alphabetUpper;
    private String shiftedAlphabetLower1;
    private String shiftedAlphabetUpper1;
    private String shiftedAlphabetLower2;
    private String shiftedAlphabetUpper2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwoKeys(int key1, int key2) {
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        shiftedAlphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0,key1);
        shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);
        shiftedAlphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input) {
        StringBuilder encryptedInput = new StringBuilder(input);
        OOCaesarCipher oocc1 = new OOCaesarCipher(mainKey1);
        OOCaesarCipher oocc2 = new OOCaesarCipher(mainKey2);
        
        for (int index=0; index < input.length(); index++) {
            if (index % 2 == 0 || index == 0) {
                encryptedInput.replace(index,index+1,oocc1.encrypt(input.substring(index,index+1)));
            }
            else {
                encryptedInput.replace(index,index+1,oocc2.encrypt(input.substring(index,index+1)));
            }
        }
        return encryptedInput.toString();
    }
}
