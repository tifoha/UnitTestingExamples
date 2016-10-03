package UnitTestingExamples.ch05;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Vitaly on 01.10.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class RaceResultsServiceTest {
    private static final String CATEGORY1 = "Category1";
    private static final String CATEGORY2 = "Category2";
    private Logger logger = mock(Logger.class);
    private RaceResultsService resultsService = new RaceResultsService(logger);
    private Message message = mock(Message.class);
    private Client clientA = mock(Client.class, "ClientA");
    private Client clientB = mock(Client.class, "ClientB");

    private static final Object[][] getMessageDateText() {
        return new Object[][]{
                {LocalDate.ofEpochDay(1), "message1"}
                , {LocalDate.ofEpochDay(2), "message2"}
                , {LocalDate.ofEpochDay(3), "message3"}
        };
    }

    @Test
    public void subscribedClientShouldReceiveMessage() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.send(message);

        verify(clientA).receive(message);
    }

    @Test
    public void subscribedClientShouldReceiveAnyNumberOfSendingMessages() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.send(message);
        resultsService.send(message);

        verify(clientA, times(2)).receive(message);
    }

    @Test
    public void allSubscribedClientsShouldRecieveMessages() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.addSubscriber(clientB);
        resultsService.send(message);

        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }

    @Test
    public void notSubscribedClientShouldNotReceiveMessage() throws Exception {
        resultsService.send(message);

        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    public void shouldSendOnlyOneMessageToMultiSubscriber() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.addSubscriber(clientA);
        resultsService.send(message);

        verify(clientA, times(1)).receive(message);
    }

    @Test
    public void unsubscribingClientShouldNotToEffectOtherClients() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.addSubscriber(clientB);
        resultsService.removeSubscriber(clientA);
        resultsService.send(message);

        verify(clientA, never()).receive(message);
        verify(clientB, times(1)).receive(message);
    }

    @Test
    public void unsubscribedClientShouldNotReceiveMessages() throws Exception {
        resultsService.addSubscriber(clientA);
        resultsService.removeSubscriber(clientA);
        resultsService.send(message);

        verify(clientA, never()).receive(message);
    }

    @Test
    public void returnFalseWhenTryToRemoveUnsubscribedClient() throws Exception {
        assertFalse(resultsService.removeSubscriber(clientA));
    }

    @Test
    public void returnTrueWhenTryToRemoveSubscribedClient() throws Exception {
        resultsService.addSubscriber(clientA);
        assertTrue(resultsService.removeSubscriber(clientA));
    }

    @Test
    public void constructorShouldSetUpLogger() throws Exception {
        resultsService = new RaceResultsService(logger);
        assertEquals(logger, resultsService.getLogger());
    }

    @Test
    @Parameters(method = "getMessageDateText")
    public void dateAndTextOfEveryMessageShouldBeLogged(LocalDate messageDate, String messageText) throws Exception {
        when(message.getDate()).thenReturn(messageDate);
        when(message.getText()).thenReturn(messageText);
        resultsService.send(message);

        verify(logger).logg(messageDate, messageText);
    }

    @Test
    public void clientSubscribedOnCategoryShouldGetMessagesByThisCategory() throws Exception {
        resultsService.addSubscriber(CATEGORY1, clientA);
        resultsService.send(CATEGORY1, message);

        verify(clientA).receive(message);
    }

    @Test
    public void clientSubscribedOnCategoryShouldGetMessagesOnlyByThisCategory() throws Exception {
        resultsService.addSubscriber(CATEGORY1, clientA);
        resultsService.send(CATEGORY1, message);
        resultsService.send(CATEGORY2, message);

        verify(clientA).receive(message);
    }

    @Test
    public void unsubscribedCategoryClientShouldNotReceiveMessages() throws Exception {
        resultsService.addSubscriber(CATEGORY1, clientA);
        resultsService.addSubscriber(CATEGORY2, clientA);
        resultsService.removeSubscriber(CATEGORY2, clientA);
        resultsService.send(CATEGORY1, message);
        resultsService.send(CATEGORY2, message);

        verify(clientA, times(1)).receive(message);
    }

}
