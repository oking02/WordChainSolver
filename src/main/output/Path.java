package main.output;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a complete valid word chain from start to finish.
 * Created by oking on 11/11/14.
 */
public class Path {
    private List<String> wordsInPath;

    public Path(){
        this.wordsInPath = new ArrayList<>();
    }

    //This allows you add words from the last to the first but resulting in a correctly ordered list for printing.
    public void addWord(String word){
        wordsInPath.add(0, word);
    }

    public List<String> getWordsInPath() {
        return wordsInPath;
    }

    public void prettyPrint(){
        for (String str : wordsInPath){
            System.out.print(str + " ");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path path = (Path) o;

        if (wordsInPath != null ? !wordsInPath.equals(path.wordsInPath) : path.wordsInPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return wordsInPath != null ? wordsInPath.hashCode() : 0;
    }
}
