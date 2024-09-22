package admin_user.fallen_soldiers;


import jakarta.persistence.*;

@Entity
@Table(name = "fallen_soldiers")
public class FallenSoldier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String story;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType; // Stores the MIME type of the image

    public FallenSoldier() {
    }

    public FallenSoldier(String name, String story, byte[] photo, String photoContentType) {
        this.name = name;
        this.story = story;
        this.photo = photo;
        this.photoContentType = photoContentType;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }
}
