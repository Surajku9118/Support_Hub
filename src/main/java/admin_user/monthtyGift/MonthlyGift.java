package admin_user.monthtyGift;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class MonthlyGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Amount is required")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Frequency is required")
    private Frequency frequency;

    @Column(nullable = false)
    @NotBlank(message = "Donor email is required")
    @Email(message = "Email should be valid")
    private String donorEmail;

    @Column(unique = true)
    private String registrationId;

    // Default constructor
    public MonthlyGift() {
    }

    // Set the registrationId based on user information
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    // Getters and Setters for other fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    @Override
    public String toString() {
        return "MonthlyGift{" +
                "id=" + id +
                ", amount=" + amount +
                ", frequency=" + frequency +
                ", donorEmail='" + donorEmail + '\'' +
                ", registrationId='" + registrationId + '\'' +
                '}';
    }

    // Enum for Frequency
    public enum Frequency {
        MONTHLY, QUARTERLY, ANNUALLY
    }
}
