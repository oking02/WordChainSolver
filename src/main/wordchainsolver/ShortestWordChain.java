package main.wordchainsolver;

import main.dictionary.Dictionary;
import main.input.WordChainInput;
import main.output.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Finds the shortest valid word chain.
 * Faster than finding all and sorting.
 * Exits as soon as first chain is found.
 * Implements a Breadth first search.
 * Created by oking on 11/11/14.
 */
public class ShortestWordChain {
    private WordChainInput input;
    private Dictionary dictionary;


    public ShortestWordChain(WordChainInput input, Dictionary dictionary) throws IOException {
        this.input = input;
        this.dictionary = dictionary;
    }

    //Starting with the root Node it finds the shortest chain.
    public Path findShortestChain(){

        Node startNode = new Node(input.getFirstWord());

        //Enqueue the root node.
        List<Node> nodeQueue = new ArrayList<>();
        nodeQueue.add(startNode);

        while (!nodeQueue.isEmpty()){

            //Dequeue node
            Node currentNode = nodeQueue.remove(0);
            createChildNodes(currentNode);

            //Check each of its childNodes.
            for (Node childNode : currentNode.childNodes){

                //If last word encounter, build that Path and exit.
                if (childNode.word.equals(input.getLastWord())){
                    return buildPath(childNode);
                }
                //Add child notes to the queue.
                else {
                    nodeQueue.add(childNode);
                    dictionary.getDictionary().remove(childNode.word);
                }
            }
        }
        return new Path();
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
