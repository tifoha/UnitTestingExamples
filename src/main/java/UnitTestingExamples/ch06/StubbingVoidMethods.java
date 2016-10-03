package UnitTestingExamples.ch06;

import java.io.PrintStream;

/**
 * Created by Vitaly on 03.10.2016.
 */
public class StubbingVoidMethods {
    private final PrintStream out;

    public StubbingVoidMethods(PrintStream out) {
        this.out = out;
    }

    public void bar(String s) {
        out.println(s);
    }
}
