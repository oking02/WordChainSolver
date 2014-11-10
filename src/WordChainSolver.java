import main.wordutils.WordList;

import java.io.IOException;
import java.util.List;

/**
 * Created by oking on 08/11/14.
 */
public class WordChainSolver {


    public static void main(String[] args) throws IOException {

        WordList wordList = new WordList();
        List<String> listOfWords = wordList.createValidWordList(4);
        System.out.println(listOfWords.size());

    }
}
