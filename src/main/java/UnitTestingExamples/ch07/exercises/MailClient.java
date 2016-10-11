package UnitTestingExamples.ch07.exercises;

/**
 * Created by Vitaly on 09.10.2016.
 */
public class MailClient {
    public void sendEmail(String address, String title, String body) {
        Email email = new Email(address, title, body);
        EmailServer.sendEmail(email);
    }
}