package tests;

import main.output.Path;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by oking on 12/11/14.
 */
public class PathTests {

    @Test
    public void testEquals(){
        Path path = new Path();
        path.addWord("One");
        path.addWord("Two");
        path.addWord("Three");
        path.addWord("Four");

        Path path1 = new Path();
        path1.addWord("One");
        path1.addWord("Two");
        path1.addWord("Three");
        path1.addWord("Four");

        assertTrue(path.equals(path1));
    }
}
