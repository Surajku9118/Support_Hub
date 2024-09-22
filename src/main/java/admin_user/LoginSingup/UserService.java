package admin_user.LoginSingup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // Use email instead of username
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public void updateUserProfile(User user, String oldPassword, String newPassword, String confirmPassword) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        
        // Check if the old password matches
        if (existingUser == null || !passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            throw new RuntimeException("Old password is incorrect.");
        }

        // Check if the new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New password and confirm password do not match.");
        }

        // Encode and set the new password
        existingUser.setPassword(passwordEncoder.encode(newPassword));

        // Update other user details if needed
        existingUser.setUsername(user.getUsername()); // Update username

        // Save the updated user
        userRepository.save(existingUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
}
