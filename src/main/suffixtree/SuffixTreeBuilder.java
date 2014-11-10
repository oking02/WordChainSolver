package main.suffixtree;

import main.wordutils.WordList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oking on 08/11/14.
 */
public class SuffixTreeBuilder {

    private String startWord;
    private String lastWord;
    private List<String> dictionary;
    private List<String> succesfulChains;
    private int depthLimit;

    public SuffixTreeBuilder(String startWord, String lastWord, int depthLimit) throws IOException {
        this.lastWord = lastWord;
        this.startWord = startWord;
        this.succesfulChains = new ArrayList<>();
        this.dictionary = buildDictionary(startWord.length());
        this.depthLimit = depthLimit;
    }

    private List<String> buildDictionary(int wordSize) throws IOException {
        WordList wordList = new WordList();
        return wordList.createValidWordList(wordSize);
    }

    public void startTree() throws IOException {

        Node startNode = new Node(startWord);
        buildTree(startNode);

    }

    private void buildTree(Node node){

        if (node.word.equals(lastWord) ){
            succesfulChains.add(buildPath(node));
        }
        else {

            if (findDepth(node) <= depthLimit){  //Limit of length of chain. Compute Time increase massively at each step.

                createChildNodes(node);
                if (!node.childNodes.isEmpty()){

                    node.childNodes.forEach(this::buildTree);
                }
            }
        }

    }

    private String buildPath(Node node){
        Node currentNode = node;
        List<String> path = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(" | ");

        while (currentNode != null){

            path.add(currentNode.word);
            currentNode = currentNode.parent;

        }

        for (int i = path.size() - 1; i >= 0 ; i--) {
            stringBuilder.append(" ").append(path.get(i)).append(" ");
        }

        return stringBuilder.append(" | ").toString();
    }

    private int findDepth(Node node){
        Node currentNode = node;
        int depth = 1;
        while (currentNode != null){
            depth++;
            currentNode = currentNode.parent;
        }
        return depth;
    }

    private void createChildNodes(Node node){

        for (String s : dictionary){

            int difference = 0;
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) != node.word.charAt(i)){
                    difference ++;
                }
            }

            if (difference == 1){
                node.childNodes.add(new Node(s, node));
            }
        }

    }

    public List<String> getSuccesfulChains() {
        return succesfulChains;
    }


    class Node{
        private String word;
        private Node parent;
        List<Node> childNodes;

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
