package application.backend.com.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request body for application")
public record ApplicationRequest (
        @NotNull(message = "Trip id cannot be null!")
        @Schema(description = "Trip ID", example = "1")
        Long tripId
){}
