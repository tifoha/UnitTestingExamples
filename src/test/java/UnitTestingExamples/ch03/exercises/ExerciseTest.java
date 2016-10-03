package UnitTestingExamples.ch03.exercises;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static UnitTestingExamples.ch03.exercises.Exercise.reverse;
import static org.junit.Assert.*;

/**
 * Created by Vitaly on 29.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class ExerciseTest {
//    private static final Object[][] getStrings() {
//        return new String[][]{
//                {""}
//                ,{"  "}
//
//        };
//    }

    @Test
//    @Parameters(method = "")
    public void reverseTest() throws Exception {
        assertEquals("", reverse(""));
        assertEquals(" ", reverse(" "));
        assertEquals("a", reverse("a"));
        assertEquals("aA", reverse("Aa"));
        assertEquals("aa", reverse("aa"));
        assertEquals("ab", reverse("ba"));
        assertEquals("abc", reverse("cba"));
    }

    @Test(expected = NullPointerException.class)
    public void reverseArgumentShouldBeNotNull() throws Exception {
        reverse(null);
    }
}