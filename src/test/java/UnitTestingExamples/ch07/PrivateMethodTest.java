package UnitTestingExamples.ch07;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class PrivateMethodTest {
    private SomeClass sut = new SomeClass();

    @Test
    public void reflectionTesting() throws Exception {
        Class[] paramTypes = {long.class};
        Method privateMethod = SomeClass.class.getDeclaredMethod("privateMethod", paramTypes);
        privateMethod.setAccessible(true);

        Object[] args = {124};
        boolean result = (boolean) privateMethod.invoke(sut, args);
        assertThat(result).isTrue();
    }

    @Test
    public void powerMockTesting() throws Exception {
        boolean result = Whitebox.invokeMethod(sut, "privateMethod", 124L);
        assertThat(result).isTrue();
    }
}
