
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WordPlay {
    public boolean isVowel(char ch) {
        boolean isAVowel = false;
        Character testChar = Character.toLowerCase(ch);
        if (testChar.toString().matches("[aeiou]")) {
            isAVowel = true;
        }
        return isAVowel;
    }
    public String replaceVowels(String phrase, char ch) {
        //Return a String that is phrase with all the vowels (upper and lower) replaced by ch
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int index=0; index < phrase.length(); index++) {
            if (isVowel(phrase.charAt(index))) {
                newPhrase.setCharAt(index,ch);
            }
        }
        return newPhrase.toString();
    }
    public String emphasize(String phrase, char ch) {
        //Return a String that is the phrase but with ch (upper or lower) replaced by
        //'*' if it is in an odd numbered location (NOT INDEX) in the String
        //'+' if it is an even numbered location (NOT INDEX) in the String
        //Example: emphasize("dna ctgaaactga",'a') would return "dn* ctg+*+ctg+"
        //Example: emphasize("Mary Bella Abracadabra",'a') would return "M+ry Bell+ +br*c*d*br+"
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int index=0; index < phrase.length() ; index++) {
            if (((index+1) % 2 == 0 || index == 0) && phrase.toLowerCase().charAt(index) == ch) {
                newPhrase.setCharAt(index,'+');
            }
            else if ((index+1) % 2 != 0 && phrase.toLowerCase().charAt(index) == ch) {
                newPhrase.setCharAt(index,'*');
            }
        }
        return newPhrase.toString();
    }
    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(phrase + " becomes " + emphasize(phrase,ch));
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(phrase + " becomes " + emphasize(phrase,ch));
    }
}
