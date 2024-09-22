package admin_user.resources;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import admin_user.LoginSingup.User;
import admin_user.LoginSingup.UserService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    // Display the form for applying to the scholarship
    @GetMapping("/apply")
    public String showApplicationForm(Model model) {
        model.addAttribute("application", new Application());
        return "apply";
    }

    // Handle form submission
    @PostMapping("/apply")
    public String submitApplicationForm(
            @Valid @ModelAttribute("application") Application application,
            BindingResult bindingResult,
            @RequestParam("resumeFile") MultipartFile resumeFile,
            @RequestParam("certificateFile") MultipartFile certificateFile,
            Model model) {

        // Validate form input
        if (bindingResult.hasErrors()) {
            return "apply"; // Redisplay the form if validation fails
        }

        // Handle file uploads (resume and certificate)
        try {
            if (!resumeFile.isEmpty()) {
                application.setResume(resumeFile.getBytes());
            }
            if (!certificateFile.isEmpty()) {
                application.setCertificate(certificateFile.getBytes());
            }
        } catch (IOException e) {
            model.addAttribute("fileUploadError", "Error uploading files. Please try again.");
            return "apply";
        }

        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Assuming the username is the email
        User user = userService.findByEmail(userEmail); // Retrieve the user by email

        if (user == null) {
            model.addAttribute("userError", "User not found. Please log in.");
            return "apply";
        }

        // Associate the application with the logged-in user
        application.setUser(user);

        // Set the submission date and initial status
        application.setSubmittedAt(LocalDateTime.now());
        application.setStatus(Application.ApplicationStatus.PENDING);

        // Save the application
        applicationService.saveApplication(application);

        return "redirect:/applySuccess";
    }

    // Display success page
    @GetMapping("/applySuccess")
    public String showApplySuccessApplication() {
        return "applySuccess"; // Returns the name of the Thymeleaf template (without the .html extension)
    }

    // Display the list of applications
    @GetMapping("/applicationList")
    public String showApplicationList() {
        return "applicationList"; // Returns the name of the Thymeleaf template (without the .html extension)
    }

    // Display the list of scholarship applications
    @GetMapping("/scholarshipapply")
    public String showScholarshipApplyList(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications); // Ensure this name is exactly "applications" in your HTML template
        return "scholarshipapply"; // Make sure this matches the HTML template name without ".html"
    }
    
    @GetMapping("/adminFinal4")
    public String showAdminFinal4List(Model model) {
        List<Application> applications = applicationService.getAllApplications(); // Fetch all applications
        model.addAttribute("applications", applications); // Add applications to the model
        return "adminFinal4"; // Returns the name of the Thymeleaf template (without the .html extension)
    }
    
 // Update application status
    @PostMapping("/apply/update/{id}/{status}")
    public String updateApplicationStatus(@PathVariable Long id, @PathVariable String status, RedirectAttributes redirectAttributes) {
        Optional<Application> optionalApplication = applicationService.findById(id);

        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            Application.ApplicationStatus newStatus;

            try {
                newStatus = Application.ApplicationStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException ex) {
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid status: " + status);
                return "redirect:/adminFinal4"; // Redirect with error message
            }

            application.setStatus(newStatus);
            applicationService.save(application);
            redirectAttributes.addFlashAttribute("successMessage", "Status updated to: " + newStatus);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Application not found for ID: " + id);
        }

        return "redirect:/adminFinal4"; // Redirect back to adminFinal4
    }

    // Edit application
    @GetMapping("/apply/edit/{id}")
    public String editApplication(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Application> optionalApplication = applicationService.findById(id);
        if (optionalApplication.isPresent()) {
            model.addAttribute("application", optionalApplication.get());
            return "editApplication"; // Thymeleaf template for editing
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Application not found for ID: " + id);
            return "redirect:/adminFinal4"; // Redirect if not found
        }
    }

    // Save edited application
    @PostMapping("/apply/edit/{id}")
    public String saveEditedApplication(@PathVariable Long id, @ModelAttribute Application application, RedirectAttributes redirectAttributes) {
        application.setId(id);
        applicationService.save(application);
        redirectAttributes.addFlashAttribute("successMessage", "Application updated successfully!");
        return "redirect:/adminFinal4"; // Redirect back to adminFinal4
    }

    // Delete application
    @PostMapping("/apply/delete/{id}")
    public String deleteApplication(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Application> optionalApplication = applicationService.findById(id);
        if (optionalApplication.isPresent()) {
            applicationService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Application deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Application not found for ID: " + id);
        }
        return "redirect:/adminFinal4"; // Redirect back to adminFinal4
    }

    




}
