package UnitTestingExamples.ch06.exercises;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by Vitaly on 07.10.2016.
 */
public class StringUtilsTest {

    @Test
    public void returnTheSameIfInputLengthIs1() throws Exception {
        String string = "s";
        assertThat(StringUtils.reverse(string)).isEqualTo(string);
    }

    @Test
    public void returnEmptyStringWhenEmpty() throws Exception {
        String string = "";
        assertThat(StringUtils.reverse(string)).isEqualTo(string);
    }

    @Test
    public void reverseStringNotEqualsToOriginalIfItLengthGreaterThan1() throws Exception {
        String string = "ab";
        assertThat(StringUtils.reverse(string)).isNotEqualTo(string);
    }

    @Test
    public void  reverseShouldBeReversible() throws Exception {
        String string =  "abcd";
        String reverse = StringUtils.reverse(string);
        assertThat(StringUtils.reverse(reverse)).isEqualTo(string);
    }

    @Test
    public void reverseDoubleCharacters() throws Exception {
        String string = "adb漢字x";
        String reverse = StringUtils.reverse(string);
        assertThat(StringUtils.reverse(reverse)).isEqualTo(string);
    }

    @Test
    public void returnNullIfInputIsNull() throws Exception {
        assertThat(StringUtils.reverse(null)).isNull();
    }
}