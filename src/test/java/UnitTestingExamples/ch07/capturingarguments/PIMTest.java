package UnitTestingExamples.ch07.capturingarguments;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Vitaly on 09.10.2016.
 */
public class PIMTest {
    private static final int SIXTY_MINUTES = 60;
    private static final LocalDateTime START_DATE = LocalDateTime.of(2016, 8, 13, 12, 0);
    private static final LocalDateTime END_DATE = START_DATE.plusMinutes(SIXTY_MINUTES);


    @Test
    public void addMeeting1() throws Exception {
        Calendar calendar = mock(Calendar.class);
        PIM sut = new PIM(calendar);
        Meeting expectedEvent = new Meeting(START_DATE, END_DATE);

        sut.addMeeting(START_DATE, SIXTY_MINUTES);
        verify(calendar).addEvent(expectedEvent);
    }

    @Test
    public void addMeeting2() throws Exception {
        Calendar calendar = mock(Calendar.class);
        PIM sut = new PIM(calendar);
        ArgumentCaptor<Meeting> captor = ArgumentCaptor.forClass(Meeting.class);

        sut.addMeeting(START_DATE, SIXTY_MINUTES);
        verify(calendar).addEvent(captor.capture());
        Meeting meeting = captor.getValue();
        assertThat(meeting.getStartDate()).isEqualTo(START_DATE);
        assertThat(meeting.getEndDate()).isEqualTo(END_DATE);
    }

}