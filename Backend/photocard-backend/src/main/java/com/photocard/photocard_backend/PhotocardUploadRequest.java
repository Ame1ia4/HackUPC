package com.photocard.photocard_backend;

import java.util.List;

public class PhotocardUploadRequest {
    private String artistName;
    private String groupName;
    private String set;
    private List<LookingForDTO> lookingForList;

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getSet() { return set; }
    public void setSet(String set) { this.set = set; }

    public List<LookingForDTO> getLookingForList() { return lookingForList; }
    public void setLookingForList(List<LookingForDTO> lookingForList) {
        this.lookingForList = lookingForList;
    }
}