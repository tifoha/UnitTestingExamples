package UnitTestingExamples.ch07.capturingarguments;


import java.time.LocalDateTime;

/**
 * Practical Unit Testing with JUnit and Mockito - source code for examples.
 * Visit http://practicalunittesting.com for more information.
 *
 * @author Tomek Kaczanowski
 */
public class PIM {

	private Calendar calendar;

	public PIM(Calendar calendar) {
		this.calendar = calendar;
	}

	public void addMeeting(LocalDateTime startDate, int durationInMinutes) {
		LocalDateTime endDate = startDate.plusMinutes(durationInMinutes);
		Meeting meeting = new Meeting(startDate, endDate);
		calendar.addEvent(meeting);
	}
}
