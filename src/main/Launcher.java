package main;

import main.input.InputValidation;
import main.input.WordChainInput;
import main.output.Path;
import main.dictionary.Dictionary;
import main.output.PathInfo;
import main.wordchainsolver.AllWordChains;
import main.wordchainsolver.ShortestWordChain;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Launcher for the word chain solver application.
 * Provides two solution. Find the shortest chain and find all the chains.
 * Finding all the chains can be computationally expensive so a chain length variable is given to limit it.
 * However some combinations can result in a long comupte time and/or give a out of memory exception.
 *
 * Arguments
 * For shortest - shortest firstword lastword
 * For all - all fistword lastword chainLimit [p]
 * [p] optional if you would like to print all chains.
 * Created by oking on 08/11/14.
 */
public class Launcher {

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        Launcher launcher = new Launcher();
        launcher.launch(args);

        long stopTime = System.currentTimeMillis();
        long runTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Run time: " + runTime + "ms");

    }

    public void launch(String args[]){

        try {

            WordChainInput input = new WordChainInput(args[1], args[2]);
            Dictionary dictionary = new Dictionary(args[1].length());
            validation(input, dictionary);

            switch (args[0]) {

                case "shortest":
                    ShortestWordChain shortestWordChain = new ShortestWordChain(input, dictionary);
                    Path path = shortestWordChain.findShortestChain();

                    if (path.getWordsInPath().isEmpty()) {
                        System.out.println("No possible path");
                    } else {
                        path.prettyPrint();
                    }
                    break;

                case "all":
                    int depth = Integer.parseInt(args[3]);

                    System.out.println("WARNING - Combinations of small word lengths and long chain lengths can use a large amount of memory.");
                    System.out.println("Can take a large amount of time and/or cause a OutOfMemoryException");
                    System.out.println("Additionally, it will time out after 10 minutes of operation.");
                    System.out.println("");

                    AllWordChains allWordChains = new AllWordChains(input, dictionary, depth);
                    List<Path> paths = allWordChains.startTree();

                    PathInfo pathInfo = new PathInfo(paths);

                    System.out.println("One of the Shortest chains");
                    pathInfo.findShortestWordChain().prettyPrint();
                    System.out.println("");
                    System.out.println("-----");

                    System.out.println("One of the Longest chains");
                    pathInfo.findLongestWordChain().prettyPrint();
                    System.out.println("");
                    System.out.println("-----");

                    Map<Integer, Integer> frequencyOfLength = pathInfo.frequencyInfomation(depth);
                    for (Integer i : frequencyOfLength.keySet()) {
                        System.out.println("WordChain of size " + i + " Frequency: " + frequencyOfLength.get(i));
                    }

                    if (args.length == 5){
                        if (args[4].equals("p")){
                            for (Path validPath : paths){
                                validPath.prettyPrint();
                                System.out.println("");
                                System.out.println("-");
                            }
                        }
                        else {
                            System.out.println("Enter p as the last argument to request the printing of all chains.");
                        }

                    }
                    break;

                default:
                    System.out.println("Please add shortest or all as the first argument");
                    break;
            }


        } catch (IOException e) {
            System.out.println("Can't find dictionary file");
        } catch (OutOfMemoryError e){
            System.out.println("Insufficient Memory to compute the tree.");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void validation(WordChainInput input, Dictionary dictionary){
        InputValidation inputValidation = new InputValidation(dictionary);
        inputValidation.isInputValid(input);
    }




}
