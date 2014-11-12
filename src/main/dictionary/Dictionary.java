package main.dictionary;

import java.io.*;
import java.util.*;

/**
 * Created by oking on 08/11/14.
 */
public class Dictionary {
    private List<String> dictionary;

    private final String LOCATION_OF_DICTIONARY_FILE = new File("").getAbsolutePath() + "/websters-dictionary.txt";

    public Dictionary(int wordLength) throws IOException {
        this.dictionary = createValidWordList(wordLength);
    }

    //Limit words those of the correct length in relation to the input variables.
    public List<String> createValidWordList(int wordLength) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(LOCATION_OF_DICTIONARY_FILE));
        String line;
        //Initially use a set to remove duplicate words that may occur after normalisation.
        Set<String> words = new HashSet<>();

        while ((line = in.readLine()) != null){

            if (line.length() == wordLength){
                words.add(line.toLowerCase(Locale.ENGLISH)); //lower case to normalise the words.
            }

        }
        //Converting to a list gives better control over the words.
        return new ArrayList<>(words);
    }


    public boolean isWordInDictionary(String word){
        return dictionary.contains(word);
    }

    public List<String> getDictionary() {
        return dictionary;
    }


}
