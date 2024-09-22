package admin_user.fallen_soldiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FallenSoldierController {

    @Autowired
    private FallenSoldierService soldierService;

    // Display all soldiers on the main page
    @GetMapping("/soldiers")
    public String getAllSoldiers(Model model) {
        model.addAttribute("soldiers", soldierService.getAllSoldiers());
        return "soldiers";  // This will map to soldiers.html (the main page showing the list of soldiers)
    }

    // Display detailed view for a single soldier
    @GetMapping("/soldiers/{id}")
    public String getSoldierById(@PathVariable("id") Long id, Model model) {
        FallenSoldier soldier = soldierService.getSoldierById(id);
        if (soldier == null) {
            // Handle the case where the soldier is not found
            return "error/404";  // Return to a 404 error page or similar
        }
        model.addAttribute("soldier", soldier);
        return "soldier-detail";  // This will map to soldier-detail.html (the detailed view of a soldier)
    }
}
