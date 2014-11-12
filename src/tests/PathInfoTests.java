package tests;

import main.output.Path;
import main.output.PathInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 12/11/14.
 */
public class PathInfoTests {

    @Test
    public void findShortestTest(){
        List<Path> listOfPaths = new ArrayList<>();

        Path path = new Path();
        path.addWord("One");
        path.addWord("Two");

        Path path2 = new Path();
        path2.addWord("One");
        path2.addWord("Two");
        path2.addWord("Three");

        Path path3 = new Path();
        path3.addWord("One");
        path3.addWord("Two");
        path3.addWord("Three");
        path3.addWord("Four");
        path3.addWord("Five");

        listOfPaths.add(path);
        listOfPaths.add(path2);
        listOfPaths.add(path3);

        PathInfo pathInfo = new PathInfo(listOfPaths);

        assertTrue(path.equals(pathInfo.findShortestWordChain()));
    }

    @Test
    public void findLongestTest(){
        List<Path> listOfPaths = new ArrayList<>();

        Path path = new Path();
        path.addWord("One");
        path.addWord("Two");

        Path path2 = new Path();
        path2.addWord("One");
        path2.addWord("Two");
        path2.addWord("Three");

        Path path3 = new Path();
        path3.addWord("One");
        path3.addWord("Two");
        path3.addWord("Three");
        path3.addWord("Four");
        path3.addWord("Five");

        listOfPaths.add(path);
        listOfPaths.add(path2);
        listOfPaths.add(path3);

        PathInfo pathInfo = new PathInfo(listOfPaths);

        assertTrue(path3.equals(pathInfo.findLongestWordChain()));
    }

    @Test
    public void testFrequencyFinder(){
        List<Path> listOfPaths = new ArrayList<>();

        Path path = new Path();
        path.addWord("One");
        path.addWord("Two");

        Path path1 = new Path();
        path1.addWord("One");
        path1.addWord("Two");
        path1.addWord("Three");

        Path path2 = new Path();
        path2.addWord("One");
        path2.addWord("Two");
        path2.addWord("Three");

        Path path3 = new Path();
        path3.addWord("One");
        path3.addWord("Two");
        path3.addWord("Three");
        path3.addWord("Four");
        path3.addWord("Five");

        listOfPaths.add(path);
        listOfPaths.add(path1);
        listOfPaths.add(path2);
        listOfPaths.add(path3);

        PathInfo pathInfo = new PathInfo(listOfPaths);
        Map<Integer, Integer> frequencyInformation = pathInfo.frequencyInfomation(5);

        assertTrue(frequencyInformation.get(2) == 1);
        assertTrue(frequencyInformation.get(3) == 2);
        assertTrue(frequencyInformation.get(4) == 0);
        assertTrue(frequencyInformation.get(5) == 1);
    }


}
