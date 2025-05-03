package com.photocard.photocard_backend;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Photocard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artistName;
    private String groupName;
    private String set;
    private String imagePath;
    private LocalDateTime uploadedAt;

    @OneToMany(mappedBy = "sourceCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LookingFor> lookingForList = new ArrayList<>();

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getSet() { return set; }
    public void setSet(String set) { this.set = set; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public List<LookingFor> getLookingForList() { return lookingForList; }
    public void setLookingForList(List<LookingFor> lookingForList) {
        this.lookingForList = lookingForList;
        for (LookingFor lf : lookingForList) {
            lf.setSourceCard(this); // ensure bidirectional link is maintained
        }
    }



}