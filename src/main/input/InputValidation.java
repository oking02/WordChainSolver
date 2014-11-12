package main.input;

import main.dictionary.Dictionary;

/**
 * Checks chosen program constraints on the input values.
 * Created by oking on 11/11/14.
 */
public class InputValidation {
    private Dictionary dictionary;

    public InputValidation(Dictionary dictionary){
        this.dictionary = dictionary;
    }

    //Using exception as its easily allow tailored error messages with minimal code.
    public void isInputValid(WordChainInput input){

        if (!areFirstAndLastWordEqualLength(input)){
            throw new IllegalArgumentException("First and last word must be of equal length");
        }

        if (!isFirstWordInDictionary(input.getFirstWord())){
            throw new IllegalArgumentException(input.getFirstWord() + " is not in the dictionary");
        }

        if (!isLastWordInDictionary(input.getLastWord())){
            throw new IllegalArgumentException(input.getLastWord() + " is not in the dictionary");
        }

        if (!canCreateAnyValidChain(input.getFirstWord())){
            throw new IllegalArgumentException(input.getFirstWord() + " is unable to create any valid chains.");
        }

        if (!canCreateAnyValidChain(input.getLastWord())){
            throw new IllegalArgumentException(input.getLastWord() + " is unable to create any valid chains.");
        }

    }

    private boolean areFirstAndLastWordEqualLength(WordChainInput input){
        return input.getFirstWord().length() == input.getLastWord().length();
    }

    private boolean isFirstWordInDictionary(String firstWord){
        return dictionary.isWordInDictionary(firstWord);
    }

    private boolean isLastWordInDictionary(String lastWord){
        return dictionary.isWordInDictionary(lastWord);
    }

    //Check to see if the input words have at least one other word in the dictionary that they can change to by changing a single character.
    //Quicker to check during input validation that let the whole program run.
    private boolean canCreateAnyValidChain(String word){
            boolean isValid = false;

            for (String str : dictionary.getDictionary()){
                int difference = 0;

                for (int i = 0; i < word.length(); i++) {

                    if (word.charAt(i) != str.charAt(i)){
                        difference ++;
                    }
                }
                if (difference == 1 && !str.equals(word)){
                    isValid = true;
                }
            }
            return isValid;
    }
}
