package admin_user.memorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemorialService {

    @Autowired
    private MemorialRepository memorialRepository;

    public Memorial saveMemorial(String heroName, String heroRank, String heroBranch, LocalDate dateOfSacrifice, 
                                 String tribute, MultipartFile photoFile) throws IOException {
        Memorial memorial = new Memorial();
        memorial.setHeroName(heroName);
        memorial.setHeroRank(heroRank);
        memorial.setHeroBranch(heroBranch);
        memorial.setDateOfSacrifice(dateOfSacrifice);
        memorial.setTribute(tribute);

        // Store photo as byte array (BLOB)
        if (!photoFile.isEmpty()) {
            memorial.setPhoto(photoFile.getBytes());
        }

        return memorialRepository.save(memorial);
    }

    public Optional<Memorial> getMemorialById(Long id) {
        return memorialRepository.findById(id);
    }

    public List<Memorial> getAllMemorials() {
        return memorialRepository.findAll();
    }
}
