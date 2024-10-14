package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
