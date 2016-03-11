package coursera.java.gladlibs;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> gladMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private Random myRandom;
    private int replacedWords;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap() {
        gladMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        replacedWords = 0;
    }

    public GladLibMap(String source) {
        gladMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        replacedWords = 0;
    }

    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal"
                , "adjective", "name", "color"
                , "timeframe", "verb", "fruit"};
        for (String label : labels) {
            ArrayList<String> list = readIt(source + "/" + label + ".txt");
            gladMap.put(label, list);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        addUsedCategory(label);
        return randomFrom(gladMap.get(label));
    }

    private void addUsedCategory(String label) {
        if (usedCategories.indexOf(label) == -1) {
            usedCategories.add(label);
        }
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        boolean wordAlreadyUsed = checkIfUsed(sub);
        while (wordAlreadyUsed) {
            sub = getSubstitute(w.substring(first + 1, last));
            wordAlreadyUsed = checkIfUsed(sub);
        }
        replacedWords++;
        return prefix + sub + suffix;
    }

    private boolean checkIfUsed(String word) {
        int index = usedWords.indexOf(word);
        if (index == -1) {
            usedWords.add(word);
            return false;
        } else {
            return true;
        }
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNumber of replaced words:\t" + replacedWords + "\n");
        totalWordsInMap();
        totalWordsConsidered();
    }

    private void totalWordsInMap() {
        //This method returns the total number of words in all the ArrayLists 
        // in the HashMap. 
        //After printing the GladLib, call this method and print out the total
        // number of words that were possible to pick from.
        int totalWords = 0;
        for (String key : gladMap.keySet()) {
            ArrayList<String> currentWordList = gladMap.get(key);
            System.out.println("Category: " + key + "\tTotal words in category: "
                    + currentWordList.size());
            totalWords += currentWordList.size();
        }
        System.out.println("Grand total of all lists: " + totalWords);
    }

    private void totalWordsConsidered() {
        //This method returns the total number of words in the ArrayLists of
        // the categories that were used for a particular GladLib. If only noun,
        // color, and adjective were the categories used in a GladLib, then only
        // calculate the sum of all the words in those three ArrayLists. 
        //Hint: You will need to keep track of the categories used in solving the 
        // GladLib, then compute this total.
        ArrayList<String> content = new ArrayList<String>();
        int grandTotal = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < usedCategories.size(); index++) {
            String category = usedCategories.get(index);
            content = gladMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            grandTotal += content.size();
        }
        System.out.println("Grand total of possible words: " + grandTotal);
    }
}
