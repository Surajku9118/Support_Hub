package admin_user.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsletterController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/test-email")
    @ResponseBody
    public String testSendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("banerjeeg2010@gmail.com"); // Replace with your email
            message.setSubject("Test Email");
            message.setText("This is a test email.");

            mailSender.send(message);
            return "Test email sent successfully!";
        } catch (Exception e) {
            return "Failed to send test email: " + e.getMessage();
        }
    }
}
