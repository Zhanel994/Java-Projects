package application.exam_7_zhanel_toishybekova.com.controllers;

import application.exam_7_zhanel_toishybekova.com.controllers.interfaces.TokenControllerAPI;
import application.exam_7_zhanel_toishybekova.com.models.Tokens;
import application.exam_7_zhanel_toishybekova.com.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequiredArgsConstructor
public class TokenController implements TokenControllerAPI
{
    private final TokenService service;

    @Override
    public ResponseEntity<Tokens> refresh(String header) throws AuthenticationException
    {
        Tokens tokens = service.refresh(header, "/api/v1/tokens/refresh");
        return ResponseEntity.ok(tokens);
    }
}
