package tests;

import main.wordutils.WordList;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by oking on 10/11/14.
 */
public class WordListTests {

    private WordList wordList = new WordList();

    @Test
    public void testNotEmpty() throws IOException {
        List<String> listOfWords = wordList.createValidWordList(2);
        assertFalse(listOfWords.isEmpty());
    }

    @Test
    public void testCorrectLengthWords() throws IOException {
        List<String> listOfWords = wordList.createValidWordList(2);
        assertTrue(listOfWords.get(0).length() == 2);
    }

    @Test(expected = IOException.class)
    public void testException() throws NoSuchFieldException, IllegalAccessException, IOException {
        Field field = WordList.class.getDeclaredField("dictionaryLocation");
        field.setAccessible(true);
        field.set(wordList, "wrong");
        wordList.createValidWordList(2);

    }
}
