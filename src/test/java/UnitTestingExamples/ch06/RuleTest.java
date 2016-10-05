package UnitTestingExamples.ch06;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitaly on 04.10.2016.
 */
public class RuleTest {
    private static final int TIMEOUT = 18;
    @Rule
    public Timeout timeout = new Timeout(20);

    @Test
    public void shouldFail() throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @Test
    public void shouldPass1() throws Exception {
        TimeUnit.MILLISECONDS.sleep(TIMEOUT);
    }

    @Test
    public void shouldPass2() throws Exception {
        TimeUnit.MILLISECONDS.sleep(TIMEOUT);
    }

    @Test
    public void shouldPass3() throws Exception {
        TimeUnit.MILLISECONDS.sleep(TIMEOUT);
    }
}
