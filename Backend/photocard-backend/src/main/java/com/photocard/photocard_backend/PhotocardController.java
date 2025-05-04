package com.photocard.photocard_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/photocards")


public class PhotocardController {

    @Autowired
    private PhotocardRepository photocardRepository;

    @GetMapping
    public List<Photocard> getAllPhotocards() {
        return photocardRepository.findAll();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhotocard(
            @RequestPart("info") PhotocardUploadRequest info,
            @RequestPart("image") MultipartFile imageFile
    ) throws IOException {

        //check if png
        if (!imageFile.getContentType().equalsIgnoreCase("image/png")) {
            return ResponseEntity
                    .badRequest()
                    .body("Only PNG files are allowed.");
        }

        // Save image to disk
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);
        Path filePath = uploadDir.resolve(filename);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Create photocard
        Photocard card = new Photocard();
        card.setArtistName(info.getArtistName());
        card.setGroupName(info.getGroupName());
        card.setSet(info.getSet());
        card.setImagePath(filename);
        card.setUploadedAt(LocalDateTime.now());

        // Add looking for
        List<LookingFor> lookingForList = new ArrayList<>();
        if (info.getLookingForList() != null) {
            for (LookingForDTO dto : info.getLookingForList()) {
                LookingFor lf = new LookingFor();
                lf.setArtistName(dto.artistName);
                lf.setGroupName(dto.groupName);
                lf.setSet(dto.set);
                lf.setImagePath(dto.imagePath);
                lf.setSourceCard(card);
                lookingForList.add(lf);
            }
        }

        card.setLookingForList(lookingForList);
        photocardRepository.save(card);

        return ResponseEntity.ok("Photocard uploaded successfully!");
    }
}