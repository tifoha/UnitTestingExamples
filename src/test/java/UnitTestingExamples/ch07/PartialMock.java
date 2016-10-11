package UnitTestingExamples.ch07;

import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

/**
 * Created by Vitaly on 09.10.2016.
 */
public class PartialMock {
    @Test
    public void test() throws Exception {
        MySut sut = spy(new MySut());
        doReturn(LocalDate.ofEpochDay(15)).when(sut).getDate();
//        when(sut.getDate()).thenReturn(LocalDate.ofEpochDay(15));
        sut.someMethod();
        verify(sut).getDate();
    }
}
