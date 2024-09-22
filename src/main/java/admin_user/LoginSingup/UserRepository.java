package admin_user.LoginSingup;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Optional but recommended for clarity

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);  // Added method to find user by email
    User findByRegistrationId(String registrationId);

}
