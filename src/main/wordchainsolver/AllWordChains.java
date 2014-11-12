package main.wordchainsolver;

import main.dictionary.Dictionary;
import main.input.WordChainInput;
import main.output.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Finds all possible word chains up to a given chain length.
 * Implements a Depth-First search.
 * Created by oking on 08/11/14.
 */
public class AllWordChains {
    private WordChainInput input;
    private Dictionary dictionary;
    private int depthLimit;
    private List<Path> listOfValidPaths;

    public AllWordChains(WordChainInput input, Dictionary dictionary, int depthLimit) throws IOException {
        this.input = input;
        this.dictionary = dictionary;
        this.depthLimit = depthLimit;
        this.listOfValidPaths = new ArrayList<>();
    }

    /*
    Builds a tree starting with the first word using the inner Node class. Node class is found at the end of the file.
    The children as all valid words that can be made by changing a single character.
    Uses a Executor and ThreadPool to parallelize the process and increase speed.
    A inner class BranchThead Runnable class is used.
     */
    public List<Path> startTree() throws IOException, InterruptedException {

        Node startNode = new Node(input.getFirstWord());
        createChildNodes(startNode);

        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        for (Node node : startNode.childNodes){
            threadPool.execute(new BranchThread(node));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(600, TimeUnit.SECONDS); // 10 Min limit

        return listOfValidPaths;
    }

    /*
    Helper method to populate the tree with all possible routes up to a specified depth.
    Is called recursively until either the depth limit is reached or the last word is encountered.
    If the last word is encountered another helper method is used to create a Path object for the chain of words leading to it and added to a list.
    If it encounters a word that appeared previously in a chain it shops that route.
     */
    private void buildTree(Node node){

        //Check for last word.
        if (node.word.equals(input.getLastWord()) ){
            listOfValidPaths.add(buildPath(node));
        }
        else {

            //Check for depth
            if (findDepthOfNodeInTree(node) <= depthLimit){  //Limit of length of chain. Compute Time increase massively at each step.

                createChildNodes(node);

                //check if no children as possible.
                if (!node.childNodes.isEmpty()){
                    for (Node childNode : node.childNodes){

                        //Check to see if word has appeared in this chain before.
                        if (wordHasNotOccurred(childNode)){
                            buildTree(childNode);
                        }
                    }
                }
            }
        }
    }

    //Helper method to build a Path object.
    private Path buildPath(Node node){
        Node currentNode = node;
        Path path = new Path();

        //Uses the parent field in Node to traverse back up the tree.
        while (currentNode != null){
            path.addWord(currentNode.word);
            currentNode = currentNode.parent;
        }
        return path;
    }

    //Helper method to check length of current branch.
    private int findDepthOfNodeInTree(Node node){
        Node currentNode = node;
        int depth = 1;

        //Uses the parent field in Node to traverse back up the tree.
        while (currentNode != null){
            depth++;
            currentNode = currentNode.parent;
        }
        return depth;
    }

    //Helper Method to create child nodes. Finds valid words one character away.
    private void createChildNodes(Node node){

        for (String s : dictionary.getDictionary()){

            int difference = 0;
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) != node.word.charAt(i)){
                    difference ++;
                }
            }

            //Only one char different and not more.
            if (difference == 1){
                node.childNodes.add(new Node(s, node));
            }
        }

    }

    //Helper method to check for words being repeated in chains.
    private boolean wordHasNotOccurred(Node node){
        Node currentNode = node.parent;
        String word = node.word;

        //Uses the parent field in Node to traverse back up the tree.
        while (currentNode != null){

            if (currentNode.word.equals(word)){
                return false;
            }
            currentNode = currentNode.parent;
        }
        return true;
    }

    //Thread that contains one of the branches from the start node.
    private class BranchThread implements Runnable{

        private Node node;
        BranchThread(Node node){
            this.node = node;
        }

        @Override
        public void run() {
            buildTree(node);
        }

    }

    //A node on the tree.
    //parent field allows traversal back up the tree.
    class Node{
        private String word;
        private Node parent;
        private List<Node> childNodes;

        Node(String word){
            this.word = word;
            this.parent = null;
            childNodes = new ArrayList<>();
        }
        Node(String word, Node parent){
            this.word = word;
            this.parent = parent;
            this.childNodes = new ArrayList<>();
        }

    }

}
