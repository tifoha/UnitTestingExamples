package UnitTestingExamples.ch07.exercises;

/**
 * Created by Vitaly on 09.10.2016.
 */
public class MailClientRefactored extends MailClient {
    private final EmailFactory emailFactory;
    private final EmailService emailService;

    public MailClientRefactored(EmailFactory emailFactory, EmailService emailService) {
        this.emailFactory = emailFactory;
        this.emailService = emailService;
    }

    @Override
    public void sendEmail(String address, String title, String body) {
        Email email = emailFactory.create(address, title, body);
        emailService.sendEmail(email);
    }
}

interface EmailFactory {

    Email create(String address, String title, String body);
}

interface EmailService {

    void sendEmail(Email email);
}