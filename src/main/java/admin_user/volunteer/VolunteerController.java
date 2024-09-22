package admin_user.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/volunteer")
    public String showForm(Model model) {
        model.addAttribute("volunteerForm", new Volunteer());
        return "volunteer";
    }

    @PostMapping({"/adminFinal/volunteer", "/adminFinal/volunteer/update/{id}"})
    public String submitOrUpdateForm(@Valid @ModelAttribute("volunteerForm") Volunteer volunteer,
                                     BindingResult result,
                                     @PathVariable(value = "id", required = false) Long id,
                                     RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "volunteer";
        }

        if (id != null) {
            volunteerService.updateVolunteer(volunteer);
            redirectAttributes.addFlashAttribute("successMessage", "Volunteer updated successfully!");
        } else {
            volunteerService.saveVolunteer(volunteer);
            redirectAttributes.addFlashAttribute("successMessage", "Volunteer registered successfully!");
        }

        return "redirect:/volunteer";
    }


    @GetMapping("/admin_login")
    public String showLoginForm() {
        return "admin_login";
    }

    @PostMapping("/admin_login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               RedirectAttributes redirectAttributes) {
        boolean isValidAdmin = volunteerService.validateVolunteer(email, phone);

        if (isValidAdmin) {
            return "redirect:/ShowAllAdmin";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or phone number.");
            return "redirect:/admin_login";
        }
    }

    @GetMapping("/adminFinal")
    public String showAdminFinalPage(Model model) {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        model.addAttribute("volunteers", volunteers);
        return "adminFinal";
    }

    @GetMapping("/adminFinal/volunteer/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        model.addAttribute("volunteerForm", volunteer);
        return "volunteer";
    }

    @GetMapping("/adminFinal/volunteer/delete/{id}")
    public String deleteVolunteer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        volunteerService.deleteVolunteer(id);
        redirectAttributes.addFlashAttribute("successMessage", "Volunteer deleted successfully!");
        return "redirect:/adminFinal";
    }

    @PostMapping("/adminFinal/volunteer/accept/{id}")
    public String acceptVolunteer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        volunteerService.acceptVolunteer(id);
        redirectAttributes.addFlashAttribute("successMessage", "Volunteer accepted!");
        return "redirect:/adminFinal/volunteer/details/" + id;
    }

    @GetMapping("/adminFinal/volunteer/details/{id}")
    public String viewVolunteerDetails(@PathVariable("id") Long id, Model model) {
        Volunteer volunteer = volunteerService.findVolunteerById(id);
        if (volunteer == null) {
            return "redirect:/adminFinal";  // Redirect to a safe page if volunteer not found
        }
        model.addAttribute("volunteer", volunteer);
        return "volunteerDetails";  // Ensure this matches your file name
    }



    @PostMapping("/adminFinal/volunteer/reject/{id}")
    public String rejectVolunteer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        volunteerService.rejectVolunteer(id);
        redirectAttributes.addFlashAttribute("successMessage", "Volunteer rejected and deleted!");
        return "redirect:/adminFinal";
    }
    
    @GetMapping("/ShowAllAdmin")
    public String showShowAllAdminPage() {
        return "ShowAllAdmin"; // Return the name of the contact view (e.g., contact.html)
    }
    
}
