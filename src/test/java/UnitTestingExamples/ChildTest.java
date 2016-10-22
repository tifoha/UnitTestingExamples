package UnitTestingExamples;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by user on 20.10.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChildTest extends PatentTest {
    @Test
    public void childTestMethod() throws Exception {
        Assert.assertFalse(false);
    }

    @Override
//    @Ignore
   @Test
    public void parentTestMethod() throws Exception {
//        Assert.assertTrue(true);
Assert.fail();

    }

}
