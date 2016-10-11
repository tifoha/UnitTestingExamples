package UnitTestingExamples.ch06.exercises;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class HelpDeskTask {

    private static final LocalDateTime VALID_LOCAL_DATE_TIME = LocalDateTime.now();
    private static final LocalDateTime SATURDAY = VALID_LOCAL_DATE_TIME.with(DayOfWeek.SATURDAY);
    private static final LocalDateTime SUNDAY = VALID_LOCAL_DATE_TIME.with(DayOfWeek.SUNDAY);
    private static final LocalDateTime FRIDAY = VALID_LOCAL_DATE_TIME.with(DayOfWeek.FRIDAY);
    private Supplier dateSupplier;
    private HelpDesk sut;
    private Issue issue;

    @Before
    public void setUp() {
        dateSupplier = mock(Supplier.class);
        sut = new HelpDesk(dateSupplier);
        issue = mock(Issue.class);
    }

    @Test
    public void willHandleIssue() throws Exception {
        when(dateSupplier.get()).thenReturn(SATURDAY);
        assertThat(sut.willHandleIssue(issue)).isFalse();

        when(dateSupplier.get()).thenReturn(SUNDAY);
        assertThat(sut.willHandleIssue(issue)).isFalse();

        when(dateSupplier.get()).thenReturn(FRIDAY.withHour(18));
        assertThat(sut.willHandleIssue(issue)).isFalse();

        when(dateSupplier.get()).thenReturn(FRIDAY.withHour(17));
        assertThat(sut.willHandleIssue(issue)).isTrue();
    }

}
