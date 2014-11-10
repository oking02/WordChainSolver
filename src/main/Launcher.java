package main;

import main.suffixtree.SuffixTreeBuilder;

import java.io.IOException;

/**
 * Created by oking on 08/11/14.
 */
public class Launcher {

    public static void main(String[] args){

        SuffixTreeBuilder suff = null;

        try {
            suff = new SuffixTreeBuilder("lead", "gold", 7);
            suff.startTree();
        } catch (IOException e) {
            System.out.println("Can't find dictionary file");
        }

        suff.getSuccesfulChains().forEach(System.out::println);
    }
}
