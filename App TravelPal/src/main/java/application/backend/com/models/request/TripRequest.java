package application.backend.com.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Request body for trip")
public record TripRequest(
        @NotBlank(message = "Trip title cannot be empty!")
        @Schema(description = "Title of the trip", example = "Trip to Germany")
        String title,

        @NotBlank(message = "City cannot be empty!")
        @Schema(description = "City of the trip", example = "Berlin")
        String city,

        @NotNull(message = "Start date cannot be null!")
        @Schema(description = "Start date of the trip", example = "2025-09-27T18:25:43.511")
        LocalDateTime startDate,

        @NotNull(message = "End date cannot be null!")
        @Schema(description = "End date of the trip", example = "2025-10-07T18:25:43.511")
        LocalDateTime endDate,

        @Min(1)
        @Schema(description = "Maximum participants of the trip", example = "10")
        int MaxParticipants,

        @NotBlank(message = "Trip description cannot be empty!")
        @Schema(description = "Description of the trip", example = "In this trip, you are going to go sightseeing and go shopping in the capital of Germany!")
        String description
) {}
