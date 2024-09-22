package admin_user.memorial;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping  // Base path for this controller
public class MemorialCopyController {

    private final MemorialService memorialService;

    // Constructor Injection for memorialService
    @Autowired
    public MemorialCopyController(MemorialService memorialService) {
        this.memorialService = memorialService;
    }

    @GetMapping("/memorialpdf")
    public String viewAllMemorials(Model model) {
        // Fetch memorials from the service and add them to the model
        List<Memorial> memorials = memorialService.getAllMemorials();
        model.addAttribute("memorials", memorials);
        return "memorialpdf";  // Return the Thymeleaf template "memorialpdf.html"
    }

    @GetMapping("/memorials")
    public String showMemorialsPage(Model model) {
        // Add any necessary data to the model here if needed
        return "memorials";  // Returns the Thymeleaf template "memorials.html"
    }
}
