package UnitTestingExamples.ch03.exercises;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vitaly on 29.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class FahrenheitCelsiusConverterTest {
    private static Object[][] getFahrenheitCelsiusPairs() {
        return new Integer[][]{
                {32, 0}
                , {98, 37}
                , {212, 100}
        };
    }

    @Test
    @Parameters(method = "getFahrenheitCelsiusPairs")
    public void shouldConvertCelsiusToFahrenheit(int fahrenheit, int celsius) {
        assertEquals(fahrenheit, FahrenheitCelsiusConverter.toFahrenheit(celsius));
    }

    @Test
    @Parameters(method = "getFahrenheitCelsiusPairs")
    public void shouldConvertFahrenheitToCelcius(int fahrenheit, int celsius) {
        assertEquals(celsius, FahrenheitCelsiusConverter.toCelcius(fahrenheit));
    }
}