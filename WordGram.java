
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    public int hashCode() {
        return myWords.toString().hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int index=0;index < myWords.length; index++) {
            ret += myWords[index] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int index=0;index < myWords.length;index++) {
            if (!myWords[index].equals(other.wordAt(index))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // This method should not alter the WordGram on which it is called.
        String[] shiftedWords = new String[out.length()];
        for (int index=0;index<out.length()-1;index++) {
            shiftedWords[index] = out.wordAt(index+1);
        }
        shiftedWords[shiftedWords.length-1] = word;
        return new WordGram(shiftedWords,0,shiftedWords.length);
    }

}