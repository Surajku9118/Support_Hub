package admin_user.monthtyGift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class MonthlyGiftController {

    private final MonthlyGiftService monthlyGiftService;

    @Autowired
    public MonthlyGiftController(MonthlyGiftService monthlyGiftService) {
        this.monthlyGiftService = monthlyGiftService;
    }

    @GetMapping("/monthly-gift")
    public String showMonthlyGiftForm(Model model) {
        model.addAttribute("monthlyGift", new MonthlyGift());
        return "monthlyGift";
    }

    // Handle the monthly gift submission
    @PostMapping("/monthly-gift")
    public String processMonthlyGift(
            @Valid MonthlyGift monthlyGift,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "monthlyGift";
        }

        try {
            // Save the monthly gift
            monthlyGiftService.saveMonthlyGift(monthlyGift);
            redirectAttributes.addFlashAttribute("successMessage", "Thank you for your monthly gift!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while processing your gift. Please try again.");
            return "monthlyGift";
        }

        return "redirect:/monthly-gift";
    }

    @GetMapping("/adminFinal1")
    public String showAdminFinal1(Model model) {
        // Fetch the list of MonthlyGifts
        List<MonthlyGift> monthlyGifts = monthlyGiftService.getAllMonthlyGifts();

        // Calculate the total amount
        double totalAmount = monthlyGifts.stream()
                                         .mapToDouble(MonthlyGift::getAmount)
                                         .sum();

        // Add the list of MonthlyGifts and total amount to the model
        model.addAttribute("monthlyGifts", monthlyGifts);
        model.addAttribute("totalAmount", totalAmount);

        return "adminFinal1";
    }
}
