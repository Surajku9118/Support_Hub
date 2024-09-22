package admin_user.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public void saveVolunteer(Volunteer volunteer) {
        // Set status to "pending" by default when saving a new volunteer
        if (volunteer.getId() == null) {
            volunteer.setStatus("pending");
        }
        volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        return volunteer.orElse(null);  // Return null if the volunteer is not found
    }

    public void updateVolunteer(Volunteer volunteer) {
        // Ensure that the volunteer exists before updating
        if (volunteerRepository.existsById(volunteer.getId())) {
            volunteerRepository.save(volunteer);
        }
    }

    public void deleteVolunteer(Long id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
        }
    }

    public boolean validateVolunteer(String email, String phone) {
        Volunteer volunteer = volunteerRepository.findByEmailAndPhone(email, phone);
        return volunteer != null;
    }

    public void acceptVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer with ID " + id + " not found"));
        volunteer.setStatus("ACCEPTED"); // Update status to ACCEPTED
        volunteerRepository.save(volunteer);
    }

    public void rejectVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer with ID " + id + " not found"));
        volunteerRepository.delete(volunteer); // Reject and delete volunteer
    }
    
    public Volunteer findVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElse(null); // Or handle not found case
    }
    
  
}
