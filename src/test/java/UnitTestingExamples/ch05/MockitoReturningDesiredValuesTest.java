package UnitTestingExamples.ch05;

import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.ranges.RangeException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vitaly on 30.09.2016.
 */
public class MockitoReturningDesiredValuesTest {

    private Car myFerrary = mock(Car.class);;

    @Test
    public void testStubbing() throws Exception {
        assertFalse(myFerrary.needsFuel());

        when(myFerrary.needsFuel()).thenReturn(true);

        assertTrue(myFerrary.needsFuel());
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException() throws Exception {
        when(myFerrary.needsFuel()).thenThrow(new RuntimeException());
        myFerrary.needsFuel();

    }

    @Test
    public void testVerification() throws Exception {
        myFerrary.driveTo("Sweet home Cherkassy");
        myFerrary.needsFuel();
        verify(myFerrary).driveTo("Sweet home Cherkassy");
        verify(myFerrary).needsFuel();

    }
}
