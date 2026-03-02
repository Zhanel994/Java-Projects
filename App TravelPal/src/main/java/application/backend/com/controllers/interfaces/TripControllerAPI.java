package application.backend.com.controllers.interfaces;

import application.backend.com.entities.User;
import application.backend.com.filters.TripFilterParameters;
import application.backend.com.models.request.TripRequest;
import application.backend.com.models.response.TripDetailsResponse;
import application.backend.com.models.response.TripResponse;
import application.backend.com.models.response.TripSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/trips")
@Tag(name = "Trip Management", description = "Operation with trips")
public interface TripControllerAPI {
    @PostMapping
    @Operation(
            summary = "Create a new trip",
            description = "Creates a new trip in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Trip creation accepted",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<TripResponse> createTrip(@RequestPart("trip")TripRequest request, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal User user) throws IOException;

    @GetMapping
    @Operation(
            summary = "Get all trips",
            description = "Returns list of all trips",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Trips retrieved successfully",
                            useReturnTypeSchema = true
                    )
            }
    )
    ResponseEntity<List<TripResponse>> getAll();

    @GetMapping("/{id}")
    @Operation(
            summary = "Get trip by id",
            description = "Returns specific trip",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Trip retrieved successfully",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Trip not found"
                    )
            }
    )
    ResponseEntity<TripDetailsResponse> getById(@PathVariable Long id);

    @PostMapping("/search")
    @Operation(
            summary = "Search trips",
            description = "Search users by title, city or description with pagination and sorting",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of trips found",
                            useReturnTypeSchema = true)
            }
    )
    ResponseEntity<List<TripSearchResponse>> search(@RequestBody TripFilterParameters filter);

}
