package UnitTestingExamples.ch04.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vitaly on 30.09.2016.
 */
public class BookingSystem {
    private List<Integer> bookedHours;

    public BookingSystem() {
        bookedHours = new ArrayList<>(24);
    }

    public List<Integer> getBookedHours() {
        return bookedHours;
    }

    public void addBooking(int hour) throws DuplicateBookingException {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException();
        }

        if (bookedHours.contains(hour)) {
            throw new DuplicateBookingException(hour);
        }
        bookedHours.add(hour);
    }

    public class DuplicateBookingException extends Exception {
        public DuplicateBookingException(int duplicateHour) {
        }

    }
}
