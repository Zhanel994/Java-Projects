package application.backend.com.models.response;

import application.backend.com.entities.enums.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationResponse(
        @NotNull(message = "Trip ID cannot be null!")
        @Schema(description = "Identifier of the trip", example = "1")
        Long id,

        @NotBlank(message = "Trip title cannot be empty!")
        @Schema(description = "Title of the trip", example = "Trip to Germany")
        String title,

        @NotBlank(message = "User cannot be empty!")
        @Schema(description = "User of the application", example = "Carlos")
        String user,

        @NotNull(message = "Status cannot be null!")
        @Schema(description = "Status of the application", example = "ACCEPTED")
        ApplicationStatus status
) {}
