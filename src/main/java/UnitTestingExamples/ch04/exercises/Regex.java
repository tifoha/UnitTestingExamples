package UnitTestingExamples.ch04.exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vitaly on 30.09.2016.
 */
public class Regex {

    public static final int MINIMAL_DIGIT_LENGTH = 3;
    public static final long EARS = 1;
    public static final long DAYS = 1;

    public List<Number> getDigits(String string) {
        if (string.isEmpty()) {
            return Collections.emptyList();
        }
        String[] digitStreams = string.split("\\D+");
        return Arrays.stream(digitStreams)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.length() >= MINIMAL_DIGIT_LENGTH)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
