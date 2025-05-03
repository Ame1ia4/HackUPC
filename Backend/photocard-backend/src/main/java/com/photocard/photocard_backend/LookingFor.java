package com.photocard.photocard_backend;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class LookingFor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artistName;     // optional
    private String groupName;      // optional
    private String set;            // optional
    private String imagePath;      // optional (for full specific match)

    @ManyToOne
    @JoinColumn(name = "photocard_id")
    @JsonBackReference
    private Photocard sourceCard;

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

    public Photocard getSourceCard() { return sourceCard; }
    public void setSourceCard(Photocard sourceCard) { this.sourceCard = sourceCard; }
}