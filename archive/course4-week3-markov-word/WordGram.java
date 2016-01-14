
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = 0;
    }
    
    public int hashCode() {
        if (myHash == 0) {
            myHash = this.toString().hashCode();
        }
        return myHash;
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
        String[] shiftedWords = new String[out.length()];
        for (int index=0;index<out.length()-1;index++) {
            shiftedWords[index] = out.wordAt(index+1);
        }
        shiftedWords[shiftedWords.length-1] = word;
        return new WordGram(shiftedWords,0,shiftedWords.length);
    }

}