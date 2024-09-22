package admin_user.monthtyGift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin_user.LoginSingup.User;
import admin_user.LoginSingup.UserService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MonthlyGiftService {

    private static final Logger logger = LoggerFactory.getLogger(MonthlyGiftService.class);

    private final MonthlyGiftRepository monthlyGiftRepository;
    private final UserService userService;

    @Autowired
    public MonthlyGiftService(MonthlyGiftRepository monthlyGiftRepository, UserService userService) {
        this.monthlyGiftRepository = monthlyGiftRepository;
        this.userService = userService;
    }

    @Transactional
    public MonthlyGift saveMonthlyGift(MonthlyGift monthlyGift) {
        try {
            // Fetch the currently logged-in user (or another user association mechanism)
            Long currentUserId = getCurrentUserId(); // Implement this method to get the current user ID
            Optional<User> userOptional = userService.findById(currentUserId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                monthlyGift.setRegistrationId(user.getRegistrationId());
            } else {
                throw new RuntimeException("User not found with ID: " + currentUserId);
            }

            // Save the monthly gift
            MonthlyGift savedGift = monthlyGiftRepository.save(monthlyGift);
            logger.info("Successfully saved monthly gift with ID: {}", savedGift.getId());
            return savedGift;
        } catch (Exception e) {
            logger.error("Error saving monthly gift: {}", e.getMessage());
            throw e;
        }
    }

    public List<MonthlyGift> getAllMonthlyGifts() {
        return monthlyGiftRepository.findAll();
    }

    // Example method to get the current user ID
    private Long getCurrentUserId() {
        // Replace with actual implementation to get current user ID
        return 1L; // Placeholder
    }
}
