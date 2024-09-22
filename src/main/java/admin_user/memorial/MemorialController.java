package admin_user.memorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
//import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/memorial")
public class MemorialController {

    @Autowired
    private MemorialService memorialService;

    @PostMapping("/submit")
    public String submitMemorial(
            @RequestParam("heroName") String heroName,
            @RequestParam("heroRank") String heroRank,
            @RequestParam("heroBranch") String heroBranch,
            @RequestParam("dateOfSacrifice") String dateOfSacrificeStr,
            @RequestParam("tribute") String tribute,
            @RequestParam("photoFile") MultipartFile photoFile,
            Model model) throws IOException {

        LocalDate dateOfSacrifice = LocalDate.parse(dateOfSacrificeStr);
        Memorial memorial = memorialService.saveMemorial(heroName, heroRank, heroBranch, dateOfSacrifice, tribute, photoFile);

        model.addAttribute("memorial", memorial);
        return "redirect:/memorial/pdf/" + memorial.getId();
    }


    // Serve Photo as Byte Stream
    @GetMapping("/photo/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Optional<Memorial> memorial = memorialService.getMemorialById(id);

        if (memorial.isPresent() && memorial.get().getPhoto() != null) {
            byte[] image = memorial.get().getPhoto();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Generate and Download PDF
    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable Long id) throws IOException {
        Optional<Memorial> memorialOpt = memorialService.getMemorialById(id);
        if (memorialOpt.isPresent()) {
            Memorial memorial = memorialOpt.get();

            // Generate PDF with photo included
            ByteArrayInputStream pdfStream = PdfGenerator.generatePdf(memorial, new ByteArrayInputStream(memorial.getPhoto()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=memorial.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfStream));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
