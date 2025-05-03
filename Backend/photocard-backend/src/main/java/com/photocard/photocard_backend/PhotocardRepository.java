package com.photocard.photocard_backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotocardRepository extends JpaRepository<Photocard, Long> {
}