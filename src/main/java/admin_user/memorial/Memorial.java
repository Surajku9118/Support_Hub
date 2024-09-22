package admin_user.memorial;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Memorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heroName;
    
    @Column(nullable = false, unique = true)
    private String heroRank;
    private String heroBranch;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfSacrifice;

    @Lob
    private String tribute;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo; // Store image as LONGBLOB

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroRank() {
        return heroRank;
    }

    public void setHeroRank(String heroRank) {
        this.heroRank = heroRank;
    }

    public String getHeroBranch() {
        return heroBranch;
    }

    public void setHeroBranch(String heroBranch) {
        this.heroBranch = heroBranch;
    }

    public LocalDate getDateOfSacrifice() {
        return dateOfSacrifice;
    }

    public void setDateOfSacrifice(LocalDate dateOfSacrifice) {
        this.dateOfSacrifice = dateOfSacrifice;
    }

    public String getTribute() {
        return tribute;
    }

    public void setTribute(String tribute) {
        this.tribute = tribute;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
