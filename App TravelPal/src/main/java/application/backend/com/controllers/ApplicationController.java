package application.backend.com.controllers;

import application.backend.com.controllers.interfaces.ApplicationControllerAPI;
import application.backend.com.entities.User;
import application.backend.com.entities.enums.ApplicationStatus;
import application.backend.com.models.request.ApplicationRequest;
import application.backend.com.models.response.ApplicationResponse;
import application.backend.com.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController implements ApplicationControllerAPI {
    private final ApplicationService service;

    @Override
    public ResponseEntity<ApplicationResponse> apply(@RequestBody ApplicationRequest request, @AuthenticationPrincipal User user) {
        ApplicationResponse response = service.createApplication(request, user);
        return ResponseEntity
                            .status(201)
                            .body(response);
    }

    @Override
    public ResponseEntity<List<ApplicationResponse>> getAllByUser( @AuthenticationPrincipal User user) {
        List<ApplicationResponse> applicationResponses = service.getApplicationsByUser(user);
        return ResponseEntity
                            .ok(applicationResponses);
    }

    @Override
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status, @AuthenticationPrincipal User creator) {
        ApplicationResponse applicationResponse = service.updateStatus(id, status, creator);
        return ResponseEntity
                            .ok(applicationResponse);
    };
}
