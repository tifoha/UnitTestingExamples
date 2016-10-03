package UnitTestingExamples.ch03.exercises;

/**
 * Created by Vitaly on 29.09.2016.
 */
public class FahrenheitCelsiusConverter {
    public static int toFahrenheit(int celsius) {
        return (int) (celsius * 1.8 + 32);
    }

    public static int toCelcius(int fahrenheit) {
        return (int) Math.round((fahrenheit - 32) / 1.8);
    }
}
