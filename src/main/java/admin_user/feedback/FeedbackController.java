package admin_user.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import admin_user.LoginSingup.User;
import admin_user.LoginSingup.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @PostMapping("/submitForm")
    public ResponseEntity<String> submitFeedback(@Valid @RequestBody Feedback feedback, Authentication authentication) {
        if (authentication != null) {
            String userEmail = authentication.getName();
            feedback.setEmail(userEmail);
            feedback.setName(authentication.getName());

            User user = userService.findByEmail(userEmail);
            if (user != null) {
                feedback.setRegistrationId(user.getRegistrationId());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        try {
            feedbackService.saveFeedback(feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting feedback.");
        }
    }
    
    @GetMapping("/submitForm")
    public String showsubmitForm() {
        return "feedback"; // Return the name of the signup view (e.g., signup.html)
    }
    
}
