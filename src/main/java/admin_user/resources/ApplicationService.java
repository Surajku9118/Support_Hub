package admin_user.resources;

import org.springframework.stereotype.Service;

import admin_user.LoginSingup.User;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Save application with user association
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    // Fetch all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Fetch all applications for a specific user
    public List<Application> getApplicationsByUser(User user) {
        return applicationRepository.findByUser(user);
    }
    
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }
    
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
