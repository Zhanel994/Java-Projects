package application.backend.com.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import application.backend.com.models.Tokens;
import application.backend.com.models.request.LoginRequest;
import application.backend.com.models.request.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/users")
@Tag(name = "User Management", description = "User registration and authentication")
public interface UserControllerAPI {
    @PostMapping("/registration")
    @Operation (
            summary = "Register a new user",
            description = "Creates a new user in the system",
            responses = {
                    @ApiResponse (
                            responseCode = "202",
                            description = "User registration accepted",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<Void> registration(@RequestBody RegistrationRequest request);

    @PostMapping("/login")
    @Operation (
            summary = "User login",
            description = "Authenticates user and returns JWT access and refresh tokens",
            responses = {
                    @ApiResponse (
                            responseCode = "200",
                            description = "Login successful, tokens returned",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<Tokens> login(@RequestBody LoginRequest request);
}
