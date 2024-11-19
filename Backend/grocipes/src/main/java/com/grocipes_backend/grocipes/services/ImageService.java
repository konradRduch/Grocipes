package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.EatDeadline;
import com.grocipes_backend.grocipes.models.Image;
import com.grocipes_backend.grocipes.repositories.EatDeadlineRepository;
import com.grocipes_backend.grocipes.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final EatDeadlineRepository eatDeadlineRepository;

    public ImageService(ImageRepository imageRepository,
                        EatDeadlineRepository eatDeadlineRepository) {
        this.imageRepository = imageRepository;
        this.eatDeadlineRepository = eatDeadlineRepository;
    }

    public String uploadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setName(imageFile.getOriginalFilename());
        image.setData(ImageUtils.compressImage(imageFile.getBytes()));
        image.setType(imageFile.getContentType());
        imageRepository.save(image);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {
        Optional<Image> dbImage = imageRepository.findByName(imageName);

        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID",  image.getId())
                        .addContextValue("Image name", imageName);
            }
        }).orElse(null);
    }

    public byte [] getImageByEatDeadlineId(Integer id){
        Optional<Image> dbImage = imageRepository.findImageByEatDeadlineId(id);
        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID",  image.getId());
            }
        }).orElse(null);
    }

    public String uploadImageById(Integer id, MultipartFile imageFile) throws IOException {
        EatDeadline eatDeadline = eatDeadlineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No EatDeadline found with ID: " + id));

        Image image = new Image();
        image.setName(imageFile.getOriginalFilename());
        image.setData(ImageUtils.compressImage(imageFile.getBytes()));
        image.setType(imageFile.getContentType());
        image.setEatDeadline(eatDeadline);
        imageRepository.save(image);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    @Transactional
    public void deleteImageById(Integer id) {
        imageRepository.deleteImageByEatDeadlineId(id);
    }
}
