package application.backend.com.controllers;

import application.backend.com.controllers.interfaces.MediaControllerAPI;
import application.backend.com.entities.Media;
import application.backend.com.services.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MediaController implements MediaControllerAPI {
    private final MediaService service;

    public ResponseEntity<byte[]> get(String name) {
        Media media = service.get(name);
        String type = media.getType().getContentType();

        return ResponseEntity
                            .ok()
                            .contentType(MediaType.parseMediaType(type))
                            .body(media.getContent());
    }
}
