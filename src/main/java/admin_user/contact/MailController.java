package admin_user.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/sendMail")
    public ModelAndView sendMail(@RequestParam String name, 
                                  @RequestParam String email, 
                                  @RequestParam String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("banerjeeg2010@gmail.com");
        mailMessage.setSubject("Contact Form Submission from " + name);
        mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);
        
        emailSender.send(mailMessage);
        
        ModelAndView modelAndView = new ModelAndView("contact"); // Return to the contact page
        modelAndView.addObject("successMessage", "Your message has been sent successfully!");
        return modelAndView;
    }
}
