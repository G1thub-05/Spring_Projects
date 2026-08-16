package in.digeshwar;

public class EmailServiceImpl implements IEmailService {
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        return true;
    }
}
