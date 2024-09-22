package admin_user.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import admin_user.LoginSingup.User;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUser(User user);

    List<Application> findByUser_RegistrationId(String registrationId);
}
