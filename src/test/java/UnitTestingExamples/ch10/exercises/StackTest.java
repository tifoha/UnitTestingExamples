package UnitTestingExamples.ch10.exercises;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Vitaly on 15.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class StackTest {
    private Stack<Object> sut;
    @Mock
    private Object elementA;
    @Mock
    private Object elementB;

    @Before
    public void setUp() throws Exception {
        sut = new Stack<>();
    }

    @Test
   public void stackShouldBeEmptyWhenCreated() throws Exception {
        assertThat(sut.size())
                .isEqualTo(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotAllowGetElementsFromEmptyStack() throws Exception {
        assertThat(sut.size())
                .isEqualTo(0);
        sut.pop();
    }

    @Test
    public void shouldAllowToPushMultipleElements() throws Exception {
        sut.push(elementA);
        sut.push(elementB);
        assertThat(sut.size())
                .isEqualTo(2);
    }

    @Test
    public void shouldAllowToPopMultipleElements() throws Exception {
        sut.push(elementA);
        sut.push(elementB);
        sut.pop();
        sut.pop();
        assertThat(sut.size())
                .isSameAs(0);
    }

    @Test
    public void shouldAllowToGetPushedElement() throws Exception {
        sut.push(elementA);
        assertThat(sut.pop())
                .isEqualTo(elementA);
    }

    @Test
    public void shouldPopElementsInBackOrderToPush() throws Exception {
        sut.push(elementA);
        sut.push(elementB);
        assertThat(sut.pop())
                .isEqualTo(elementB);
        assertThat(sut.pop())
                .isEqualTo(elementA);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotAllowPopElementsAfterLastElementWasPopped() throws Exception {
        sut.push(elementA);
        sut.pop();
        sut.pop();
    }

}
