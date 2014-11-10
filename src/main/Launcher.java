package main;

import main.suffixtree.WordChainSuffixTree;

import java.io.IOException;

/**
 * Created by oking on 08/11/14.
 */
public class Launcher {

    public static void main(String[] args){


        WordChainSuffixTree wordChainTree;

        if (args.length == 0){

            try {

                wordChainTree = new WordChainSuffixTree("lead", "gold", 7);
                wordChainTree.startTree();
                wordChainTree.getSuccessfulChains().forEach(System.out::println);

            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            } catch (OutOfMemoryError e){
                System.out.println("Insufficient Memory to compute the tree.");
            }

        }
        else {

            try {

                wordChainTree = new WordChainSuffixTree(args[0], args[1], Integer.parseInt(args[2]));
                wordChainTree.startTree();
                wordChainTree.getSuccessfulChains().forEach(System.out::println);

            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            } catch (OutOfMemoryError e){
                System.out.println("Insufficient Memory to compute the tree.");
            }

        }
    }
}
