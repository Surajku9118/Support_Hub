package admin_user.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    
    // Find a volunteer by email and phone
    Volunteer findByEmailAndPhone(String email, String phone);

    // Find volunteers by status
    List<Volunteer> findByStatus(String status);
    

}
