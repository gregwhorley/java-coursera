
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class Tester {
    public void testCaesarCipher() {
        FileResource fileResource = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(fileResource.asString());
        System.out.println("Encrypted message text:\n"+encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message text:\n"+decrypted);
    }
    
    public void testCaesarCrackerWithNoArgs() {
        FileResource fileResource = new FileResource();
        CaesarCracker cc = new CaesarCracker();
        String message = cc.decrypt(fileResource.asString());
        System.out.println("Decrypted message using no args for constructor:\n"+message);
    }
    
    public void testCaesarCrackerWithArgs() {
        FileResource fileResource = new FileResource();
        CaesarCracker cc = new CaesarCracker('a');
        String message = cc.decrypt(fileResource.asString());
        System.out.println("Decrypted message using 'a' arg for constructor:\n"+message);
    }
    
    public void testVigenereCipher() {
        FileResource fileResource = new FileResource();
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String encrypted = vc.encrypt(fileResource.asString());
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Vigenere cipher encrypted message:\n"+encrypted);
        System.out.println("Vigenere cipher decrypted message:\n"+decrypted);
    }
}
