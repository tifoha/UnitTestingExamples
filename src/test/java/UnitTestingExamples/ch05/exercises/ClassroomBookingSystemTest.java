package UnitTestingExamples.ch05.exercises;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Vitaly on 01.10.2016.
 */
public class ClassroomBookingSystemTest {
    private static final String CLASSROOM_A_NAME = "A";
    private static final String CLASSROOM_B_NAME = "B";
    private static final DayOfWeek VALID_DAY_OF_WEEK = DayOfWeek.MONDAY;
    private static final int VALID_START_HOUR = 9;
    private static final int VALID_HOURS_COUNT = 2;
    private ClassroomBookingSystem bookingSystem = new ClassroomBookingSystem();
    private Classroom classroomA = mock(Classroom.class, "ClassroomA");
    private Classroom classRoomB = mock(Classroom.class, "ClassroomB");

    @Before
    public void setUp() throws Exception {
        when(classroomA.getName()).thenReturn(CLASSROOM_A_NAME);
        when(classRoomB.getName()).thenReturn(CLASSROOM_B_NAME);

    }

    @Test
    public void classroomListShouldBeEmptyWhenCreated() throws Exception {
        Collection<Classroom> allClassrooms = bookingSystem.getAllClassrooms();
        assertNotNull(allClassrooms);
        assertTrue(allClassrooms.isEmpty());
    }

    @Test
    public void shouldListAllAddedClassrooms() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.addClassroom(classRoomB);
        Collection<Classroom> allClassrooms = bookingSystem.getAllClassrooms();

        assertTrue(allClassrooms.contains(classroomA));
        assertTrue(allClassrooms.contains(classRoomB));
        assertEquals(2, allClassrooms.size());
    }

    @Test
    public void shouldNotAllowToAddSameClassroom() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.addClassroom(classroomA);
        Collection<Classroom> allClassrooms = bookingSystem.getAllClassrooms();

        assertTrue(allClassrooms.contains(classroomA));
        assertEquals(1, allClassrooms.size());
    }

    @Test
    public void shouldNotAllowToAddClassroomWithSameName() throws Exception {
        bookingSystem.addClassroom(classroomA);
        when(classRoomB.getName()).thenReturn(CLASSROOM_A_NAME);
        bookingSystem.addClassroom(classRoomB);
        Collection<Classroom> allClassrooms = bookingSystem.getAllClassrooms();

        assertTrue(allClassrooms.contains(classroomA));
        assertEquals(1, allClassrooms.size());
    }

    @Test
    public void addClassroomShoulReturnTrueIfSuccess() throws Exception {
        assertTrue(bookingSystem.addClassroom(classroomA));
    }

    @Test
    public void addClassroomSeoulReturnFalseIfClassroomIsAlreadyAdded() throws Exception {
        bookingSystem.addClassroom(classroomA);
        assertFalse(bookingSystem.addClassroom(classroomA));
    }

    @Test
    public void bookedClassroomIsNotAvailableAtTheSameTimeAndHours() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.book(classroomA.getName(), VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        Collection<Classroom> availableClassrooms = bookingSystem.getAvailableClassrooms(VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        assertFalse(availableClassrooms.contains(classroomA));
    }

    @Test
    public void bookShouldReturnFalseIfClassroomNotFound() throws Exception {
        assertFalse(bookingSystem.book(CLASSROOM_A_NAME, VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT));
    }

    @Test
    public void bookShouldReturnTrueIfClassroomBooked() throws Exception {
        bookingSystem.addClassroom(classroomA);
        assertTrue(bookingSystem.book(CLASSROOM_A_NAME, VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT));
    }

    @Test
    public void bookShouldReturnFalseIfClassroomIsBookedAtTheSameDayHourAndHourCount() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.book(classroomA.getName(), VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        assertFalse(bookingSystem.book(classroomA.getName(), VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT));
        Collection<Classroom> availableClassrooms = bookingSystem.getAvailableClassrooms(VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        assertFalse(availableClassrooms.contains(classroomA));
    }

    @Test
    public void bookedClassroomShouldBeAvailableAfterBookingEnded() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.book(classroomA.getName(), VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        Collection<Classroom> availableClassrooms = bookingSystem.getAvailableClassrooms(VALID_DAY_OF_WEEK, VALID_START_HOUR + VALID_HOURS_COUNT, VALID_HOURS_COUNT);
        assertTrue(availableClassrooms.contains(classroomA));
    }

    @Test
    public void bookingOneClassroomShouldNotEffectOtherClassrooms() throws Exception {
        bookingSystem.addClassroom(classroomA);
        bookingSystem.addClassroom(classRoomB);
        bookingSystem.book(classroomA.getName(), VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        Collection<Classroom> availableClassrooms = bookingSystem.getAvailableClassrooms(VALID_DAY_OF_WEEK, VALID_START_HOUR, VALID_HOURS_COUNT);
        assertThat(availableClassrooms).contains(classRoomB);
//        assertTrue(availableClassrooms.contains(classRoomB));
    }
}
