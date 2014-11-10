package main.wordutils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by oking on 10/11/14.
 */
public class WordChainSorting {

    private List<String> unSortedWordChains;

    public WordChainSorting(List<String> unSortedWordChains){
        this.unSortedWordChains = unSortedWordChains;
    }

    public int numberOfWordChains(){
        return unSortedWordChains.size();
    }


    public String findShortestWordChain(){
        Comparator<String> byLength = (e1, e2) -> Integer.compare(
                e1.length(), e2.length());

        List<String> sortedChains = unSortedWordChains
                .stream()
                .sorted(byLength)
                .collect(Collectors.toList());

        return sortedChains.get(0);
    }

    public Map<Integer, Integer> occurances(int wordSize){

        int charPerWord = wordSize + 2;
        Set<Integer> wordLengths = new HashSet<>();
        for (String s : unSortedWordChains){
            wordLengths.add(s.length() / charPerWord);
        }

        Map<Integer, Integer> occurancesOfLengths = new HashMap<>();
        for (Integer ints : wordLengths){
            occurancesOfLengths.put(ints, 0);
        }

        for (String s : unSortedWordChains){
            occurancesOfLengths.put(s.length() / charPerWord, occurancesOfLengths.get(s.length() / charPerWord) + 1);
        }

        return occurancesOfLengths;
    }

}
