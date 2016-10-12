package UnitTestingExamples.ch07.exercises;

/**
 * Created by Vitaly on 09.10.2016.
 */
public class MailClientRefactored extends MailClient{
    public void sendEmail(String address, String title, String body) {
        Email email = getEmail(address, title, body);
        sendEmail(email);
    }

    protected void sendEmail(Email email) {
        EmailServer.sendEmail(email);
    }

    protected Email getEmail(String address, String title, String body) {
        return new Email(address, title, body);
    }
}