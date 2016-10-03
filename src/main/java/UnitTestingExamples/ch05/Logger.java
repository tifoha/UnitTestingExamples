package UnitTestingExamples.ch05;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Vitaly on 01.10.2016.
 */
public interface Logger {
    void logg(LocalDate date, String text);
}
