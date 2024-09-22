package admin_user.monthtyGift;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthlyGiftRepository extends JpaRepository<MonthlyGift, Long> {

    // Find gifts by frequency
    List<MonthlyGift> findByFrequency(MonthlyGift.Frequency frequency);
}
