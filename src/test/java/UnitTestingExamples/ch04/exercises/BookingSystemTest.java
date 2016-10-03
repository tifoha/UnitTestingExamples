package UnitTestingExamples.ch04.exercises;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vitaly on 30.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {

    private static final int VALID_HOUR = 1;
    private BookingSystem bookingSystem = new BookingSystem();

    private static final Object[] getIllegalHourValues() {
        return new Integer[]{-1, 24};
    }

    private static final Object[] getLegalHourValues() {
        return new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    }

    @Test
    public void shouldReturnList() throws Exception {
        assertNotNull(bookingSystem.getBookedHours());
        assertTrue(bookingSystem.getBookedHours() instanceof List);
    }

    @Test
    public void shouldReturnEmptyListWhenCreated() throws Exception {
        assertTrue(bookingSystem.getBookedHours().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getIllegalHourValues")
    public void hourShouldBeBetween0and23(int hour) throws Exception {
        bookingSystem.addBooking(hour);
    }

    @Test
    @Parameters(method = "getLegalHourValues")
    public void addBookingTest(int hour) throws Exception {
        bookingSystem.addBooking(hour);
        List<Integer> bookedHours = bookingSystem.getBookedHours();
        assertEquals(1, bookedHours.size());
        assertTrue(bookedHours.contains(hour));
        assertEquals(hour, (int) bookedHours.get(0));
    }

    @Test(expected = BookingSystem.DuplicateBookingException.class)
    public void throwExceptionIfTimeIsAlredyBooked() throws Exception {
        bookingSystem.addBooking(VALID_HOUR);
        bookingSystem.addBooking(VALID_HOUR);
    }
}
