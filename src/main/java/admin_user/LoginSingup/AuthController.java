package admin_user.LoginSingup;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import admin_user.volunteer.Volunteer;

@Controller
@RequestMapping
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthController(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Controller
    public class MyController {

        @Autowired
        private UserService userService;

        @GetMapping("/")
        public String showIndex(Principal principal, Model model) {
            String username = "Guest";
            String registrationId = "N/A"; // Default value if user is not logged in

            // Check if the user is logged in
            if (principal != null) {
                // Get the email (which is the username in Spring Security by default)
                username = principal.getName();

                // Fetch the User entity by email (username)
                User user = userRepository.findByEmail(username);

                if (user != null) {
                    // Set the registrationId from the User entity
                    registrationId = user.getRegistrationId();
                }
            }

            // Add the username and registrationId to the model
            model.addAttribute("username", username);
            model.addAttribute("registrationId", registrationId);

            // Return the index view
            return "index";
        }
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Return the name of the signup view (e.g., signup.html)
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            return "signup";
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        
     // Generate unique registration ID based on saved user's ID
        user.generateRegistrationId();
        userRepository.save(user); // Save again to update with registrationId

        return "redirect:/login"; // Redirect to login page after successful registration
    }
    
    
    @GetMapping("/adminFinal3")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminFinal3";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the name of the login view (e.g., login.html)
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; // Return the name of the about view (e.g., about.html)
    }
    

    @GetMapping("/getinvolved")
    public String getInvolvedPage() {
        return "getinvolved"; // This refers to getinvolved.html in /templates folder
    }
    
    @GetMapping("/community")
    public String showCommunityPage() {
        return "community"; // Return the name of the contact view (e.g., contact.html)
    }

    @GetMapping("/help")
    public String showHelpPage() {
        return "help"; // Return the name of the help view (e.g., help.html)
    }


    @GetMapping("/resources")
    public String showResourcesPage() {
        return "resources"; // Return the name of the resources view (e.g., resources.html)
    }

    @GetMapping("/volunteerpage")
    public String showVolunteerPage() {
        return "volunteerpage"; // Return the name of the volunteer view (e.g., volunteer.html)
    }

    // Add a method to display the user's profile
    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the current user's email
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile"; // Return the name of the profile view (e.g., profile.html)
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("user") User user, String oldPassword, String newPassword, String confirmPassword, Model model) {
        try {
            // Add logic to check the old password before updating
            userService.updateUserProfile(user, oldPassword, newPassword, confirmPassword);
            model.addAttribute("successMessage", "Profile updated successfully!");
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/login"; // Redirect back to profile page
    }
    
}
