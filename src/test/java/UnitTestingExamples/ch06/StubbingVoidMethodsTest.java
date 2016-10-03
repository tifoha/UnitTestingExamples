package UnitTestingExamples.ch06;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * Created by Vitaly on 03.10.2016.
 */
public class StubbingVoidMethodsTest {
    @Test
    public void fooShouldPrintHelloWorld() throws Exception {
        PrintStream out = mock(PrintStream.class);
        String string = "Hello world";
        doNothing().when(out).println(string);
        StubbingVoidMethods sut = new StubbingVoidMethods(out);
        sut.bar(string);
        verify(out).println(anyString());
    }
}
