package UnitTestingExamples.ch06.exercises;

/**
 * Created by Vitaly on 07.10.2016.
 */
public class StringUtils {
    public static String reverse(String in) {
        if (in == null) {
            return null;
        }

        return new StringBuilder(in)
                .reverse()
                .toString();
    }
    
}
