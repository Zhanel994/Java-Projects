package application.backend.com.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record TripDetailsResponse(
        @NotNull(message = "Trip ID cannot be null!")
        @Schema(description = "Identifier of the trip", example = "1")
        Long id,

        @NotBlank(message = "Trip description cannot be empty!")
        @Schema(description = "Description of the trip", example = "In this trip, you are going to go sightseeing and go shopping in the capital of Germany!")
        String description,

        @NotNull(message = "Start date cannot be null!")
        @Schema(description = "Start date of the trip", example = "2025-09-27T18:25:43.511")
        LocalDateTime startDate,

        @NotNull(message = "End date cannot be null!")
        @Schema(description = "End date of the trip", example = "2025-10-07T18:25:43.511")
        LocalDateTime endDate,

        @Min(1)
        @Schema(description = "Maximum participants of the trip", example = "10")
        int maxParticipants,

        @Schema(description = "Free places for the trip", example = "7")
        int freePlaces,

        @Schema(description = "Participants of the trip", example = "Alex, Max, Charles")
        List<String> participants
) {}
