
/**
 * Write a description of VigenereTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class VigenereTester {
    public void testSliceString() {
        String tester = "abcdefghijklm";
        VigenereBreaker vb = new VigenereBreaker();
        String slicedString = vb.sliceString(tester,0,3);
        System.out.println("0,3 produced: "+slicedString);
        slicedString = vb.sliceString(tester,1,3);
        System.out.println("1,3 produced: "+slicedString);
        slicedString = vb.sliceString(tester,2,3);
        System.out.println("1,3 produced: "+slicedString);
        slicedString = vb.sliceString(tester,0,4);
        System.out.println("0,4 produced: "+slicedString);
        slicedString = vb.sliceString(tester,1,4);
        System.out.println("1,4 produced: "+slicedString);
        slicedString = vb.sliceString(tester,2,4);
        System.out.println("2,4 produced: "+slicedString);
        slicedString = vb.sliceString(tester,3,4);
        System.out.println("3,4 produced: "+slicedString);
        slicedString = vb.sliceString(tester,0,5);
        System.out.println("0,5 produced: "+slicedString);
        slicedString = vb.sliceString(tester,1,5);
        System.out.println("1,5 produced: "+slicedString);
        slicedString = vb.sliceString(tester,2,5);
        System.out.println("2,5 produced: "+slicedString);
        slicedString = vb.sliceString(tester,3,5);
        System.out.println("3,5 produced: "+slicedString);
        slicedString = vb.sliceString(tester,4,5);
        System.out.println("4,5 produced: "+slicedString);
    }
    
    public void testTryKeyLength() {
        FileResource fileResource = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        String fileString = fileResource.asString();
        //test with athens_keyflute.txt
        int[] decryptKeys = vb.tryKeyLength(fileString, 5, 'e');
        System.out.println("Keys:");
        for (int index=0;index < decryptKeys.length;index++) {
            System.out.println(decryptKeys[index]);
        }
    }
}
