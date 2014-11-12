package main.input;

/**
 * Input object for the word chain solver.
 * Makes the rest of the program code easier to read by using a dedicated input object.
 * Created by oking on 11/11/14.
 */
public class WordChainInput {

    private String firstWord;
    private String lastWord;

    public WordChainInput(String firstWord, String lastWord){
        this.firstWord = firstWord.toLowerCase();
        this.lastWord = lastWord.toLowerCase();
    }

    public String getFirstWord() {
        return firstWord;
    }

    public String getLastWord() {
        return lastWord;
    }


}
