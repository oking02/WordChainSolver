package tests;

import main.suffixtree.SuffixTreeBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 10/11/14.
 */
public class SuffixTreeTests {

    private SuffixTreeBuilder suffixTreeBuilder;

    @Test
    public void test() throws IOException {
        suffixTreeBuilder = new SuffixTreeBuilder("beef", "sale", 6);
        suffixTreeBuilder.startTree();
        List<String> wordChains = suffixTreeBuilder.getSuccessfulChains();
        assertTrue(wordChains.size() == 4);
    }
}
