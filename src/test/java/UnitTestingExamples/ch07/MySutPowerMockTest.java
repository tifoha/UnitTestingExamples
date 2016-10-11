package UnitTestingExamples.ch07;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by Vitaly on 08.10.2016.
 */
@PrepareForTest(MySut.class)
@RunWith(PowerMockRunner.class)
public class MySutPowerMockTest {
    @Test
    public void tightCouplingTest() throws Exception {
        MySut sut = new MySut();
        MyCollaborator collaborator = mock(MyCollaborator.class);
        whenNew(MyCollaborator.class)
                .withAnyArguments()
                .thenReturn(collaborator);

        sut.someMethod();
        verify(collaborator).doSomething();
    }
}
