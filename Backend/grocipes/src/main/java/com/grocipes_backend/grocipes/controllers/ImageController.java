package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.Image;
import com.grocipes_backend.grocipes.repositories.ImageRepository;
import com.grocipes_backend.grocipes.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/images")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Metoda do dodawania obrazów
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file); // Logika przesyłania
        // Zwracaj odpowiedź w formacie JSON
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", uploadImage));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }

    @GetMapping("/downloadImage/{id}")
    public ResponseEntity<?> downloadImageByEatDeadlineId(@PathVariable Integer id) {
        byte[] imageData = imageService.getImageByEatDeadlineId(id);

        if (imageData == null || imageData.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No image data found for EatDeadline ID: " + id);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }
    @PostMapping("/upload/{id}")
    public ResponseEntity<?>uploadImageById(@PathVariable Integer id, @RequestParam("image") MultipartFile file)throws IOException{
        String uploadImage = imageService.uploadImageById(id,file); // Logika przesyłania
        // Zwracaj odpowiedź w formacie JSON
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", uploadImage));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImageById(@PathVariable Integer id){
        imageService.deleteImageById(id);
        return ResponseEntity.ok().build();
    }

}
