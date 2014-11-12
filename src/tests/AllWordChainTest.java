package tests;

import main.dictionary.Dictionary;
import main.input.WordChainInput;
import main.output.Path;
import main.wordchainsolver.AllWordChains;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 11/11/14.
 */
public class AllWordChainTest {

    private WordChainInput input;
    private Dictionary dictionary;
    private AllWordChains allWordChains;

    @Test
    public void testDuplicatePaths() throws IOException, InterruptedException {
        input = new WordChainInput("lead", "gold");
        dictionary = new Dictionary(4);
        allWordChains = new AllWordChains(input, dictionary, 6);

        boolean noDuplicates = true;
        List<Path> listOfPaths = allWordChains.startTree();

        for (Path path : listOfPaths){
            for (Path path1 : listOfPaths){
                if (path.equals(path1) && !path.equals(path1)){
                    noDuplicates = false;
                }
            }
        }
        assertTrue(noDuplicates);
    }
}
