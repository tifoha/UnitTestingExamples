package UnitTestingExamples.ch06.exercises;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class HelpDesk {
    public final static int EOB_HOUR = 17;
    private final Supplier<LocalDateTime> currentDateSupplier;

    public HelpDesk(Supplier<LocalDateTime> currentDateSupplier) {
        this.currentDateSupplier = currentDateSupplier;
    }

    public boolean willHandleIssue(Issue issue) {
        LocalDateTime date = currentDateSupplier.get();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (DayOfWeek.SUNDAY == dayOfWeek || DayOfWeek.SATURDAY == dayOfWeek) {
            return false;
        }
        if (DayOfWeek.FRIDAY == dayOfWeek) {
            int hour = date.getHour();
            if (hour > EOB_HOUR) {
                return false;
            }
        }
        return true;
    }
}

interface Issue {
}

