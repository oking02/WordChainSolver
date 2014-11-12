package tests;

import main.dictionary.Dictionary;
import main.input.InputValidation;
import main.input.WordChainInput;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by oking on 11/11/14.
 */
public class InputValidationTests {

    private InputValidation inputValidation;


    @Test(expected = IllegalArgumentException.class)
    public void equalsLengthTest() throws IOException {

        Dictionary dictionary = new Dictionary(4);
        inputValidation = new InputValidation(dictionary);
        WordChainInput wordChainInput = new WordChainInput("lead", "golden");
        inputValidation.isInputValid(wordChainInput);

    }

    @Test(expected = IllegalArgumentException.class)
    public void firstWordInDictionaryTest() throws IOException {

        Dictionary dictionary = new Dictionary(4);
        inputValidation = new InputValidation(dictionary);
        WordChainInput wordChainInput = new WordChainInput("leadzz", "gold");
        inputValidation.isInputValid(wordChainInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lastWordInDictionaryTest() throws IOException {

        Dictionary dictionary = new Dictionary(4);
        inputValidation = new InputValidation(dictionary);
        WordChainInput wordChainInput = new WordChainInput("lead", "goldzzz");
        inputValidation.isInputValid(wordChainInput);

    }

    @Test(expected = IllegalArgumentException.class)
    public void canCreateValidChainTest() throws IOException {

        Dictionary dictionary = new Dictionary(4);
        inputValidation = new InputValidation(dictionary);
        WordChainInput wordChainInput = new WordChainInput("prunitrin", "gold");
        inputValidation.isInputValid(wordChainInput);

    }
}
