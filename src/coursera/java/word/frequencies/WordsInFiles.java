package coursera.java.word.frequencies;
/**
 * Write a description of WordsInFiles here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordInFilesMap;

    public WordsInFiles() {
        wordInFilesMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File file) {
        //This method should add all the words from file into the map.
        //If a word is not in the map, then you must create a new ArrayList of
        // type String with this word, and have the word map to this ArrayList.
        //If a word is already in the map, then add the current filename to its
        // ArrayList, unless the filename is already in the ArrayList.
        FileResource fileResource = new FileResource(file);
        String fileName = file.getName();
        for (String word : fileResource.words()) {
            if (!wordInFilesMap.containsKey(word)) {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(fileName);
                wordInFilesMap.put(word, newList);
            } else if (wordInFilesMap.containsKey(word)
                    && !wordInFilesMap.get(word).contains(fileName)) {
                ArrayList<String> currentList = wordInFilesMap.get(word);
                currentList.add(fileName);
                wordInFilesMap.put(word, currentList);
            }
        }
    }

    private void buildWordFileMap() {
        wordInFilesMap.clear();
        DirectoryResource dirResource = new DirectoryResource();
        for (File file : dirResource.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    private int maxNumber() {
        //returns the maximum number of files any word appears in, considering
        // all words from a group of files.
        int highest = 0;
        for (String word : wordInFilesMap.keySet()) {
            ArrayList<String> currentFileList = wordInFilesMap.get(word);
            int currentNum = currentFileList.size();
            if (currentNum > highest) {
                highest = currentNum;
            }
        }
        return highest;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        //returns an ArrayList of words that appear in exactly number files
        ArrayList<String> wordList = new ArrayList<String>();
        for (String word : wordInFilesMap.keySet()) {
            ArrayList<String> currentList = wordInFilesMap.get(word);
            int currentFileCount = currentList.size();
            if (currentFileCount == number) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    private void printFilesIn(String word) {
        //prints the names of the files this word appears in, one filename per line
        ArrayList<String> fileNames = wordInFilesMap.get(word);
        for (int index = 0; index < fileNames.size(); index++) {
            System.out.println(fileNames.get(index));
        }
    }

    public void tester() {
        //call buildWordFileMap to select files and build HashMap of words
        buildWordFileMap();
        //determine maximum number of files any word is in, considering all words
        int fileNum = maxNumber();
        System.out.println("Max number files any word is in: " + fileNum);

        ArrayList<String> wordsInFiles = wordsInNumFiles(fileNum);
        System.out.println("Total words in all files: " + wordsInFiles.size());
        wordsInFiles = wordsInNumFiles(4);
        System.out.println("Total words in four files: " + wordsInFiles.size());
        printFilesIn("laid");
        System.out.println("\n");
        printFilesIn("tree");
        //System.out.println("\nList of words that appear in most files: "+wordsInFiles);
        /**
         *for (int index=0; index < wordsInFiles.size(); index++) {
         *    System.out.println("Files where "+wordsInFiles.get(index)+" appear:");
         *    printFilesIn(wordsInFiles.get(index));
         *}
         *
         *for (String key : wordInFilesMap.keySet()) {
         *    System.out.println("\nWord: "+key+"\tAppears in files: "
         *                        +wordInFilesMap.get(key));
         *}
         */
    }
}
