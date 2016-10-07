package UnitTestingExamples.ch06;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class RetryTestExecute {
    private int counter;

    @Rule
    public RuntimeExceptionCatcher catcher = new RuntimeExceptionCatcher(3);

    @Test
    public void shouldFail2times() throws Exception {
        System.out.println("executing: " + ++counter);
        Assert.fail("failing: " + counter);
    }
}
