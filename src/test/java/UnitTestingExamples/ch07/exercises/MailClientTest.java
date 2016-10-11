package UnitTestingExamples.ch07.exercises;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Created by Vitaly on 11.10.2016.
 */
@PrepareForTest({MailClient.class, EmailServer.class})
//@PrepareForTest(EmailServer.class)
@RunWith(PowerMockRunner.class)
public class MailClientTest {

    private static final String ADDRESS = "address";
    private static final String TITLE = "title";
    private static final String BODY = "body";

    private Email email;
    private MailClient sut;
    private ArgumentCaptor<Email> emailCaptor;

    @Before
    public void setUp() throws Exception {
        sut = new MailClient();
        email = Mockito.mock(Email.class);
        emailCaptor = ArgumentCaptor.forClass(Email.class);

    }

    @Test
    public void sendEmailPowerMock() throws Exception {
        PowerMockito.whenNew(Email.class)
                .withArguments(anyString(), anyString(), anyString())
                .thenReturn(email);

        //prepare static class
        PowerMockito.spy(EmailServer.class);
        PowerMockito.doNothing()
                .when(EmailServer.class, "sendEmail", any(Email.class));

        //Invoke sut
        sut.sendEmail(ADDRESS, TITLE, BODY);

        //VERIFY
        //verify new Email(Email)
        PowerMockito.verifyNew(Email.class)
                .withArguments(ADDRESS, TITLE, BODY);

        //verify EmailServer.sendMail(Email)
        verifyStatic();
//        EmailServer.sendEmail(new Email("", "", "sdfas")); //Для проверки аргументов в статическом методе нужно юзать каптор
        EmailServer.sendEmail(emailCaptor.capture());
        Assertions.assertThat(emailCaptor.getValue())
                .isEqualTo(email);
    }

    @Test
    public void sendEmailMockito() throws Exception {
        EmailFactory emailFactory = Mockito.mock(EmailFactory.class);
        Mockito.when(emailFactory.create(anyString(), anyString(), anyString()))
                .thenReturn(email);

        EmailService emailService = Mockito.mock(EmailService.class);
        Mockito.doNothing()
                .when(emailService)
                .sendEmail(any(Email.class));

        InOrder order = inOrder(emailFactory, emailService);


        sut = new MailClientRefactored(emailFactory, emailService);
        sut.sendEmail(ADDRESS, TITLE, BODY);


        order.verify(emailFactory).create(ADDRESS, TITLE, BODY);
        order.verify(emailService).sendEmail(email);
    }
}