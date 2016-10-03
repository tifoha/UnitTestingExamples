package UnitTestingExamples;

import UnitTestingExamples.ch03.Money;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.sun.javafx.PlatformUtil.isLinux;
import static com.sun.javafx.PlatformUtil.isWindows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class AppTest{


    private String string;

    @Before
    public void setUpW() throws Exception {
        assumeTrue(isWindows());
        string = "Windows";
    }

//    @Before
//    public void setUpL() throws Exception {
//        assumeTrue(isLinux());
//        string = "Linux";
//    }

    @Test
    public void name() throws Exception {
//        catchException(new Money(10, ""));
//        System.out.println(caughtException());
////        assumeTrue(isWindows());
////        assertTrue(false);
//
//        System.out.println(string + " OS");
    }
}
