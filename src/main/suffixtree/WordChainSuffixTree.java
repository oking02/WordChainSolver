package main.suffixtree;

import main.wordutils.WordList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by oking on 08/11/14.
 */
public class WordChainSuffixTree {
    private String startWord;
    private String lastWord;
    private List<String> dictionary;
    private List<String> successfulChains;
    private int depthLimit;

    public WordChainSuffixTree(String startWord, String lastWord, int depthLimit) throws IOException {
        this.lastWord = lastWord;
        this.startWord = startWord;
        this.successfulChains = new ArrayList<>();
        this.dictionary = buildDictionary(startWord.length());
        this.depthLimit = depthLimit;
    }

    private List<String> buildDictionary(int wordSize) throws IOException {
        WordList wordList = new WordList();
        return wordList.createValidWordList(wordSize);
    }


    public void startTree() throws IOException, InterruptedException {
        Node startNode = new Node(startWord);

        // Single-Threaded

        //buildTree(startNode);

        // Multi-Threaded

        createChildNodes(startNode);

        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        for (Node node : startNode.childNodes){
            threadPool.execute(new BranchThread(node));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(600, TimeUnit.SECONDS); // 10 Min limit

    }

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

    private void buildTree(Node node){

        if (node.word.equals(lastWord) ){
            successfulChains.add(buildPathFromNodeToRootNode(node));
        }
        else {

            if (findDepthOfNodeInTree(node) <= depthLimit){  //Limit of length of chain. Compute Time increase massively at each step.

                createChildNodes(node);

                if (!node.childNodes.isEmpty()){
                    node.childNodes.forEach(this::buildTree);
                }
            }
        }
    }

    private String buildPathFromNodeToRootNode(Node tailNode){
        Node traversalNode = tailNode;
        List<String> path = new ArrayList<>();

        while (traversalNode != null){
            path.add(traversalNode.word);
            traversalNode = traversalNode.parent;
        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = path.size() - 1; i >= 0 ; i--) {
            stringBuilder
                    .append(" ")
                    .append(path.get(i))
                    .append(" ");
        }
        return stringBuilder.append("").toString();
    }

    private int findDepthOfNodeInTree(Node node){
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


    public List<String> getSuccessfulChains() {
        return successfulChains;
    }

    public String getStartWord() {
        return startWord;
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
