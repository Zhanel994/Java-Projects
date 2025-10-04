package application.backend.com.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for user login")
public record LoginRequest(
        @NotBlank(message = "Username cannot be empty!")
        @Schema(description = "Username or email of the user", example = "Whisky")
        String username,

        @NotBlank(message = "User password cannot be empty!")
        @Schema(description = "Password of the user", example = "Whisky12345")
        String password
) {}
