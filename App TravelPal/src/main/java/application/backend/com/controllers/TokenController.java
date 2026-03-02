package application.backend.com.controllers;

import application.backend.com.controllers.interfaces.TokenControllerAPI;
import application.backend.com.models.Tokens;
import application.backend.com.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequiredArgsConstructor
public class TokenController implements TokenControllerAPI {
    private final TokenService service;

    @Override
    public ResponseEntity<Tokens> refresh(String header) throws AuthenticationException {
        Tokens tokens = service.refresh(header);
        return ResponseEntity
                            .ok(tokens);
    }
}
