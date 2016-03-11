
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    }
    
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        //String st = "this is a test yes this is really a test yes a test this is wow";
        int order = 3;
        int size = 200;
        int seed = 371;
        st = st.replace('\n', ' ');
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //MarkovWordTwo markovWord = new MarkovWordTwo();
        //MarkovWord markovWord = new MarkovWord(order);
        EfficientMarkovWord markovWord = new EfficientMarkovWord(order);
        runModel(markovWord, st, size, seed);
    } 
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        int size = 100;
        int seed = 42;
        int order = 2;
        
        MarkovWord markovWord = new MarkovWord(order);
        long startTime = System.currentTimeMillis();
        runModel(markovWord, st, size, seed);
        long endTime = System.currentTimeMillis();
        System.out.println(markovWord+" took "+(endTime-startTime)+"ms");
        
        EfficientMarkovWord efficientMarkov = new EfficientMarkovWord(order);
        startTime = System.currentTimeMillis();
        runModel(efficientMarkov, st, size, seed);
        endTime = System.currentTimeMillis();
        System.out.println(efficientMarkov+" took "+(endTime-startTime)+"ms");
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
