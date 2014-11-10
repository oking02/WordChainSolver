package tests;

import main.suffixtree.WordChainSuffixTree;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 10/11/14.
 */
public class SuffixTreeTests {

    private WordChainSuffixTree wordChainSuffixTree;

    @Test
    public void test() throws IOException {
        wordChainSuffixTree = new WordChainSuffixTree("beef", "sale", 6);
        wordChainSuffixTree.startTree();
        List<String> wordChains = wordChainSuffixTree.getSuccessfulChains();
        assertTrue(wordChains.size() == 4);
    }
}
