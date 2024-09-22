package admin_user.feedback;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email // Validate email format
    private String email;

    @NotBlank
    @Size(min = 1, max = 100) // Set a size constraint for name
    private String name;

    @NotBlank
    private String comment;

    private int rating;

    private String registrationId; // Field to hold registrationId

    // No-argument constructor (required by JPA)
    public Feedback() {}

    // Constructor with parameters
    public Feedback(String email, String name, String comment, int rating, String registrationId) {
        this.email = email;
        this.name = name;
        this.comment = comment;
        this.rating = rating;
        this.registrationId = registrationId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", registrationId='" + registrationId + '\'' +
                '}';
    }
}
