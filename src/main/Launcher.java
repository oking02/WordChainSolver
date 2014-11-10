package main;

import main.suffixtree.SuffixTreeBuilder;

import java.io.IOException;

/**
 * Created by oking on 08/11/14.
 */
public class Launcher {

    public static void main(String[] args){

        SuffixTreeBuilder suff;

        if (args.length == 0){

            try {

                suff = new SuffixTreeBuilder("lead", "gold", 4);
                suff.startTree();
                suff.getSuccessfulChains().forEach(System.out::println);

            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            }

        }
        else {

            try {

                suff = new SuffixTreeBuilder(args[0], args[1], Integer.parseInt(args[2]));
                suff.startTree();
                suff.getSuccessfulChains().forEach(System.out::println);

            } catch (IOException e) {
                System.out.println("Can't find dictionary file");
            }

        }
    }
}
