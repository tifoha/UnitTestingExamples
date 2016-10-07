package UnitTestingExamples.ch06;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class AtomicGeneratorTest {
    private IdGenerator sut = new AtomicGenerator();
    private Set<Long> set = new HashSet<>(100);

    @Rule
    public ConcurrentRule concurrentRule = new ConcurrentRule();

    @Rule
    public RepeatingRule repeatingRule = new RepeatingRule();

    @Test
    public void idsShouldBeUnique() throws Exception {
        long id1 = sut.generate();
        long id2 = sut.generate();
        Assert.assertNotSame(id1, id2);
    }

    @Test
    @Concurrent(count = 3)
    @Repeating(repetition = 1000)
    public void concurrentGeneratorTest() throws Exception {
        assertTrue(set.add(sut.generate()));
    }
}
