package main;

import main.suffixtree.WordChainSuffixTree;
import main.wordutils.WordChainSorting;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by oking on 08/11/14.
 */
public class Launcher {

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        WordChainSuffixTree wordChainTree = null;

        if (args.length == 0){

            try {

                wordChainTree = new WordChainSuffixTree("lead", "gold", 6);
                wordChainTree.startTree();
               // wordChainTree.getSuccessfulChains().forEach(System.out::println);

            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            } catch (OutOfMemoryError e){
                System.out.println("Insufficient Memory to compute the tree.");
            } catch (InterruptedException e) {
                System.out.println("Executor Timeout");
            }

        }
        else {

            try {

                wordChainTree = new WordChainSuffixTree(args[0], args[1], Integer.parseInt(args[2]));
                wordChainTree.startTree();
                //wordChainTree.getSuccessfulChains().forEach(System.out::println);



            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            } catch (OutOfMemoryError e){
                System.out.println("Insufficient Memory to compute the tree.");
            } catch (InterruptedException e) {
                System.out.println("Executor Timeout");
            }

        }

        long stopTime = System.currentTimeMillis();
        long runTime = stopTime - startTime;
        System.out.println("Run time: " + runTime + "ms");

        if (!wordChainTree.getSuccessfulChains().isEmpty()){

            WordChainSorting wordChainSorting = new WordChainSorting(wordChainTree.getSuccessfulChains());

            System.out.println("Number of Possible Word Chains: " + wordChainSorting.numberOfWordChains());
            System.out.println("Shortest Word Chain: " + wordChainSorting.findShortestWordChain());

            Map<Integer, Integer> occurancesOfChainLengths = wordChainSorting.occurances(wordChainTree.getStartWord().length());

            for (Integer ints : occurancesOfChainLengths.keySet()){
                System.out.println("WordChain of size " + ints + " Frequency: " + occurancesOfChainLengths.get(ints));
            }



        }else {
            System.out.println("No Word Chains Found. Try a higher chain length.");
        }




    }


}
