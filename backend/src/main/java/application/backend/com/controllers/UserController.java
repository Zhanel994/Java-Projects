package application.backend.com.controllers;

import application.backend.com.controllers.interfaces.UserControllerAPI;
import application.backend.com.models.Tokens;
import application.backend.com.models.request.LoginRequest;
import application.backend.com.models.request.RegistrationRequest;
import application.backend.com.services.AuthenticationService;
import application.backend.com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerAPI {
    private final UserService service;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<Void> registration(RegistrationRequest request) {
        service.create(request);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Tokens> login(LoginRequest request) {
        Tokens tokens = authenticationService.authenticate(
                request.username(),
                request.password()
        );

        return ResponseEntity
                            .ok(tokens);
    }
}
