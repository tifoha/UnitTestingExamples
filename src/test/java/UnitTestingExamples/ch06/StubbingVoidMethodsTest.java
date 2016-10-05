package UnitTestingExamples.ch06;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * Created by Vitaly on 03.10.2016.
 */
public class StubbingVoidMethodsTest {

    private PrintStream out = mock(PrintStream.class);
    private static final String HELLO_WORLD = "Hello world";

    @Test
    public void fooShouldPrintHelloWorld() throws Exception {

        doNothing().when(out).println(HELLO_WORLD);
        StubbingVoidMethods sut = new StubbingVoidMethods(out);
        sut.bar(HELLO_WORLD);
        verify(out).println(anyString());

        doAnswer(invocationOnMock -> {
            String arg = (String) invocationOnMock.getArguments()[0];
            System.err.println(arg);
            return arg;
        }).when(out).println(anyString());

        sut.bar(HELLO_WORLD);

        verify(out, verificationData -> {}).println(anyString());

    }
}
