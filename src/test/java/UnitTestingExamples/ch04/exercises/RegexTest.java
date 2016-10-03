package UnitTestingExamples.ch04.exercises;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

//import static org.junit.Assert.assertNotNull;

/**
 * Created by Vitaly on 30.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class RegexTest {
    private static final Object[] getStringsWithoutAppropriateDigits() {
        return new String[]{
                ""
                ,"  "
                ,"qwer"
                ,"qwerq1"
                ,"qwerqw12"
                ,"qwer1ty"
        };
    }

    private static final Object[][] getStringCountPairs() {
        return new Object[][]{
                {"12", 0}
                , {"123", 1}
                , {"1234", 1}
                , {"123 456", 2}
                , {"asd123asdf adf456afasdf", 2}
                , {"asd123asdfadf456afas346df", 3}
        };
    }

    @Test
    @Parameters(method = "getStringsWithoutAppropriateDigits")
    public void shouldNotReturnNull(String string) throws Exception {
        Regex regex = new Regex();
        assertNotNull(regex.getDigits(string));
    }

    @Test
    @Parameters(method = "getStringsWithoutAppropriateDigits")
    public void shouldReturnEmptyList(String string) throws Exception {
        Regex regex = new Regex();
        assertEquals(0, regex.getDigits(string).size());
    }

    @Test
    @Parameters(method = "getStringCountPairs")
    public void shouldReturnNotEmptyList(String string, int listSize) throws Exception {
        Regex regex = new Regex();
        assertEquals(listSize, regex.getDigits(string).size());
    }
}
