package in.digeshwar;

public class UserService {

    private IPwdWService pwdWService;
    private IEmailService emailService;

    public UserService(IPwdWService ps, IEmailService es) {
        this.pwdWService = ps;
        this.emailService = es;
    }

    public void register(String username, String email) {

        // save in db later
        String randpwd = pwdWService.generatepwd(8);
        String subject = "User Registration";
        String body = "Your Generate Password is : " + randpwd;
        String to = ": " + email;
        boolean UserEmail = emailService.sendEmail(to, subject, body);

        if (UserEmail) {
            System.out.println("Account created successfully");
        }
        else {
            System.out.println("Something went wrong");
        }
    }
}
