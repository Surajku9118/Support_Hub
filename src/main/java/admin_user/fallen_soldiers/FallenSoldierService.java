package admin_user.fallen_soldiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FallenSoldierService {

    @Autowired
    private FallenSoldierRepository soldierRepository;

    public List<FallenSoldier> getAllSoldiers() {
        return soldierRepository.findAll();
    }

    public FallenSoldier getSoldierById(Long id) {
        return soldierRepository.findById(id).orElseThrow(() -> new RuntimeException("Soldier not found"));
    }
}

