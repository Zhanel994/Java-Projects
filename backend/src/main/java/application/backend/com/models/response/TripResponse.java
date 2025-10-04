package application.backend.com.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Response body for trip")
public record TripResponse(
        @NotNull(message = "Trip ID cannot be null!")
        @Schema(description = "Identifier of the trip", example = "1")
        Long id,

        @NotBlank(message = "City cannot be empty!")
        @Schema(description = "City of the trip", example = "Berlin")
        String city,

        @NotNull(message = "Start date cannot be null!")
        @Schema(description = "Start date of the trip", example = "2025-09-27T18:25:43.511")
        LocalDateTime startDate,

        @NotNull(message = "End date cannot be null!")
        @Schema(description = "End date of the trip", example = "2025-10-07T18:25:43.511")
        LocalDateTime endDate,

        @Schema(description = "Free places for the trip", example = "7")
        int freePlaces
) {}
