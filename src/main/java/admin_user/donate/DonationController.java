package admin_user.donate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class DonationController {

    @Autowired
    private DonationService donationService;

    // Display the donation form
    @GetMapping("/donate")
    public String showDonationForm(Model model) {
        // Initialize a new Donation object and add it to the model
        model.addAttribute("donation", new Donation());
        return "donate"; // Return the donate.html template
    }

    // Process the submitted donation form
    @PostMapping("/donate")
    public String processDonationForm(@Valid @ModelAttribute("donation") Donation donation,
                                      BindingResult result, Model model) {
        // Check if there are any validation errors
        if (result.hasErrors()) {
            return "donate"; // Return the donate.html template if errors exist
        }

        // Save the donation object using the donation service
        donationService.saveDonation(donation);

        // Add a success message to the model to display on the page
        model.addAttribute("successMessage", "Thank you for your donation!");

        // Reset the donation form by adding a new Donation object
        model.addAttribute("donation", new Donation());

        // Return the donate.html template
        return "donate";
    }
    @GetMapping("/adminFinal2")
    public String showAllDonations(Model model) {
        List<Donation> donations = donationService.findAllDonations();
        
        // Calculate total sum of donations
        BigDecimal totalAmount = donations.stream()
                .map(Donation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        model.addAttribute("donations", donations);
        model.addAttribute("totalAmount", totalAmount);
        return "adminFinal2";
    }
    
  @GetMapping("/donationOptions")
  public String showDonationOptionsPage() {
      return "donationOptions"; // Return the name of the contact view (e.g., contact.html)
  }
    
}
