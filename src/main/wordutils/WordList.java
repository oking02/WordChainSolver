package main.wordutils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oking on 08/11/14.
 */
public class WordList {

    private String dictionaryLocation = new File("").getAbsolutePath() + "/websters-dictionary.txt";

    public List<String> createValidWordList(int wordLength) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(dictionaryLocation));
        String line;
        List<String> listOfWords = new ArrayList<>();

        while ((line = in.readLine()) != null){

            if (line.length() == wordLength){
                line.toLowerCase();
                listOfWords.add(line);
            }

        }
        return listOfWords;
    }
}
