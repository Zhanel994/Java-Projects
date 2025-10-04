package application.backend.com.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/media")
@Tag(name = "Media Management", description = "Upload and retrieve media files")
public interface MediaControllerAPI {

    @GetMapping("/{name}")
    @Operation(
            summary = "Get media file by name",
            description = "Returns the file content by name",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "File returned successfully",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<byte[]> get(@PathVariable String name) throws IOException;

}
