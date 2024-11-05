package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByName(String name);
}
