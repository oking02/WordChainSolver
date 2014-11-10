package main.wordutils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oking on 08/11/14.
 */
public class WordList {

    private String dictionaryLocation = new File("").getAbsolutePath() + "/websters-dictionary.txt";

    public List<String> createValidWordList(int wordLength) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(dictionaryLocation));
        String line;
        Set<String> words = new HashSet<>();

        while ((line = in.readLine()) != null){

            if (line.length() == wordLength){ //TODO need to fix duplicate entries.
                words.add(line.toLowerCase());
            }

        }
        return new ArrayList<>(words);
    }
}
