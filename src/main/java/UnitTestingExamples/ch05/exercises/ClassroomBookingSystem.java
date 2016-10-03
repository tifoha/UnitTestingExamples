package UnitTestingExamples.ch05.exercises;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Vitaly on 01.10.2016.
 */
public class ClassroomBookingSystem {
    private Map<Classroom, Map<DayOfWeek, Collection<Integer>>> classroomsForBooking = new HashMap<>();

    public Collection<Classroom> getAllClassrooms() {
        return classroomsForBooking.keySet();
    }

    public boolean addClassroom(Classroom classroom) {
        if (classroomsForBooking.containsKey(classroom) || findClassroomByName(classroom.getName()).isPresent()) {
            return false;
        }

        classroomsForBooking.put(classroom, new HashMap<>());
        return true;
    }

    private Optional<Classroom> findClassroomByName(String name) {
        return classroomsForBooking.keySet().stream()
                .filter(classroom -> name.equals(classroom.getName()))
                .findAny();
    }

    public boolean book(String classroomName, DayOfWeek dayOfWeek, int startHour, int hoursCount) {
        Optional<Classroom> classroom = findClassroomByName(classroomName);
        if (!classroom.isPresent()) {
            return false;
        }


        if (hoursAlreadyBooked(classroom.get(), dayOfWeek, startHour, hoursCount)) {
            return false;
        }

        Collection<Integer> hoursToBook = getHoursToBook(startHour, hoursCount);
        book(classroom.get(), dayOfWeek, hoursToBook);

        return true;
    }

    private void book(Classroom classroom, DayOfWeek dayOfWeek, Collection<Integer> hoursToBook) {
        Map<DayOfWeek, Collection<Integer>> bookedDays = classroomsForBooking.computeIfAbsent(classroom, cr -> new HashMap<>());
        Collection<Integer> bookedHours = bookedDays.computeIfAbsent(dayOfWeek, day -> new HashSet<>());
        bookedHours.addAll(hoursToBook);
    }

    private boolean hoursAlreadyBooked(Classroom classroom, DayOfWeek dayOfWeek, int startHour, int hoursCount) {
        if (!classroomsForBooking.containsKey(classroom)) {
            return false;
        }

        Map<DayOfWeek, Collection<Integer>> bookedDays = classroomsForBooking.computeIfAbsent(classroom, cr -> new HashMap<>());

        if (!bookedDays.containsKey(dayOfWeek)) {
            return false;
        }

        Collection<Integer> bookedHours = bookedDays.computeIfAbsent(dayOfWeek, day -> new HashSet<>());

        if (bookedHours.isEmpty()) {
            return false;
        }

        Collection<Integer> hoursToBook = getHoursToBook(startHour, hoursCount);
        boolean hoursAlreadyBooked = bookedHours.stream().anyMatch(hoursToBook::contains);

        return hoursAlreadyBooked;
    }

    private Collection<Integer> getHoursToBook(int startHour, int hoursCount) {
        return IntStream.range(startHour, startHour + hoursCount).boxed().collect(Collectors.toList());
    }

    public Collection<Classroom> getAvailableClassrooms(DayOfWeek dayOfWeek, int startHour, int hoursCount) {
        List<Classroom> availableClassrooms = classroomsForBooking.keySet().stream()
                .filter(classroom -> !hoursAlreadyBooked(classroom, dayOfWeek, startHour, hoursCount))
                .collect(Collectors.toList());
        return availableClassrooms;
    }
}
