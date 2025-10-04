package application.backend.com.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import static application.backend.com.utils.RegexConstants.EMAIL_PATTERN;

public record UserResponse(
        @Schema(description = "Identifier of the user", example = "1")
        @NotNull(message = "User ID cannot be null")
        Long id,

        @NotBlank(message = "Username cannot be empty!")
        @Size(max = 25)
        @Schema(description = "Username of the user", example = "Whisky")
        String username,

        @NotBlank(message = "User email cannot be empty!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Not valid email")
        @Schema(description = "Email of the user", example = "username")
        String email,

        @Schema(description = "Name of the user", example = "Whisky")
        String name,

        @Schema(description = "Creation date of the user", example = "2023-09-28T10:15:30")
        LocalDateTime createdAt
) {
}
