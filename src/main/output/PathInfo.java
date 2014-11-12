package main.output;

import java.util.*;
import java.util.stream.Collectors;

/**
 * For the find all chains function.
 * Using Path objects it works out information of the resulting paths.
 * Includes shortest, longest and frequency of different length chains.
 * Created by oking on 11/11/14.
 */
public class PathInfo {

    private List<Path> listOfValidPaths;

    public PathInfo(List<Path> listOfValidPaths){
       this.listOfValidPaths = listOfValidPaths;
    }


    public Map<Integer, Integer> frequencyInfomation(int lengthLimit){

        Map<Integer, Integer> frequencyOfLength = new HashMap<>();
        for (int i = 2; i <= lengthLimit ; i++) {
            frequencyOfLength.put(i, 0);
        }

        for (Path path : listOfValidPaths){
            int pathLength = path.getWordsInPath().size();
            frequencyOfLength.put(pathLength,frequencyOfLength.get(pathLength) + 1);
        }
        return frequencyOfLength;
    }

    //sorts using words list size of Path objects.
    public Path findShortestWordChain() {
        Comparator<Path> byLength = (e1, e2) ->
                Integer.compare(e1.getWordsInPath().size(), e2.getWordsInPath().size());

        List<Path> sortedChains = listOfValidPaths
                .stream()
                .sorted(byLength)
                .collect(Collectors.toList());

        return sortedChains.get(0);
    }

    //sorts using words list size of Path objects.
    public Path findLongestWordChain(){
        Comparator<Path> byLength = (e1, e2) ->
                Integer.compare(e1.getWordsInPath().size(), e2.getWordsInPath().size());

        List<Path> sortedChains = listOfValidPaths
                .stream()
                .sorted(byLength)
                .collect(Collectors.toList());

        return sortedChains.get(sortedChains.size() - 1);
    }

}
