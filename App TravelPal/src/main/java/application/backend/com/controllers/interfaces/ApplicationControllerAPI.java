package application.backend.com.controllers.interfaces;

import application.backend.com.entities.User;
import application.backend.com.entities.enums.ApplicationStatus;
import application.backend.com.models.request.ApplicationRequest;
import application.backend.com.models.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/applications")
@Tag(name = "Application Management", description = "Application with trips")
public interface ApplicationControllerAPI {
    @PostMapping
    @Operation(
            summary = "Apply to a trip",
            description = "Creates a new trip in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "User apply accepted",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<ApplicationResponse> apply(@RequestBody ApplicationRequest request, @AuthenticationPrincipal User user);

    @GetMapping("/user")
    @Operation(
            summary = "Applications of the user",
            description = "List of applied applications",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "User list apply accepted",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<List<ApplicationResponse>> getAllByUser(@AuthenticationPrincipal User user);

    @PutMapping("/{id}/status")
    @Operation(
            summary = "Update application status",
            description = "Trip creator can accept or reject another person.",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Application accepted",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status, @AuthenticationPrincipal User creator);
}
