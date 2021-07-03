package com.stomatology.service;

import com.stomatology.constants.FileStorageConstants;
import com.stomatology.entity.Image;
import com.stomatology.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image save(MultipartFile file) throws IOException {
        Image image = new Image();

        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get(FileStorageConstants.STATIC_IMAGES_DIR + fileName);

        if (!Files.exists(Paths.get(FileStorageConstants.STATIC_IMAGES_DIR))) {
            Files.createDirectories(Path.of(FileStorageConstants.STATIC_IMAGES_DIR));
        }

        Files.write(path, file.getBytes());

        image.setPath(fileName);
        image.setName(file.getOriginalFilename());

        return imageRepository.save(image);
    }
}
