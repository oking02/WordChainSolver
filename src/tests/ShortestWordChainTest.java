package tests;

import main.dictionary.Dictionary;
import main.input.WordChainInput;
import main.output.Path;
import main.wordchainsolver.AllWordChains;
import main.wordchainsolver.ShortestWordChain;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 12/11/14.
 */
public class ShortestWordChainTest {

    private WordChainInput input;
    private Dictionary dictionary;
    private ShortestWordChain shortestWordChain;

    //Need to add parameter testing to test more cases.
    @Test
    public void testIsShortestChain() throws IOException {

        input = new WordChainInput("lead", "gold");
        dictionary = new Dictionary(4);
        shortestWordChain = new ShortestWordChain(input, dictionary);
        Path expectedPath = new Path();
        expectedPath.addWord("gold");
        expectedPath.addWord("goad");
        expectedPath.addWord("load");
        expectedPath.addWord("lead");

        Path resultPath = shortestWordChain.findShortestChain();

        assertTrue(expectedPath.equals(resultPath));
    }
}
