package tests;

import main.dictionary.Dictionary;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 12/11/14.
 */
public class DictionaryTests {

    private final int NUMBER_OF_4_LETTER_WORDS = 4896;
    private final int NUMBER_OF_5_LETTER_WORDS = 9782;
    private final int NUMBER_OF_6_LETTER_WORDS = 17266;

    @Test
    public void testCorrectNumberOf4LetterWords() throws IOException {
        Dictionary dictionary = new Dictionary(4);
        assertTrue(dictionary.getDictionary().size() == NUMBER_OF_4_LETTER_WORDS);
    }

    @Test
    public void testCorrectNumberOf5LetterWords() throws IOException {
        Dictionary dictionary = new Dictionary(5);
        assertTrue(dictionary.getDictionary().size() == NUMBER_OF_5_LETTER_WORDS);
    }

    @Test
    public void testCorrectNumberOf6LetterWords() throws IOException {
        Dictionary dictionary = new Dictionary(6);
        assertTrue(dictionary.getDictionary().size() == NUMBER_OF_6_LETTER_WORDS);
    }

    @Test
    public void testForDuplicatedWords() throws IOException {
        Dictionary dictionary = new Dictionary(4);
        Set<String> setOfWords = new HashSet<>(dictionary.getDictionary());
        assertTrue(dictionary.getDictionary().size() == setOfWords.size());
    }

}
