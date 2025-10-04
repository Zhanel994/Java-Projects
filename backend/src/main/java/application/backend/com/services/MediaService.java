package application.backend.com.services;

import application.backend.com.entities.Media;
import application.backend.com.entities.enums.MediaType;
import application.backend.com.exceptions.NotFoundException;
import application.backend.com.repositories.MediaRepository;
import application.backend.com.utils.ImageCompressor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository repository;

    public Media create(MultipartFile file) throws IOException {
        String extension = getExtension(file.getOriginalFilename());
        MediaType type = MediaType.getByExtension(extension);

        String name = "%s.%s".formatted(UUID.randomUUID(), extension);

        byte[] bytes = file.getBytes();
        byte[] resized = ImageCompressor.compress(bytes, type);

        Media media = build(name, type, resized);

        return repository.save(media);
    }

    public Media get(String name) {
        return findByName(name);
    }

    private Media findByName(String name) {
        return repository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Media with name " + name + "not found!"));
    }

    private Media build(String name, MediaType type, byte[] bytes) {
        return new Media(
                name,
                "/media/%s".formatted(name),
                bytes,
                type
        );
    }

    private String getExtension(String name) {
        if (name == null || !name.contains(".")) {
            throw new IllegalArgumentException("Invalid file name!");
        }

        return name.substring(name.lastIndexOf('.') + 1).toLowerCase();
    }
}
