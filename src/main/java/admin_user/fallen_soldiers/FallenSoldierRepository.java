package admin_user.fallen_soldiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FallenSoldierRepository extends JpaRepository<FallenSoldier, Long> {
}
