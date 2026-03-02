package application.exam_7_zhanel_toishybekova.com.controllers;

import application.exam_7_zhanel_toishybekova.com.controllers.interfaces.UserControllerAPI;
import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.Tokens;
import application.exam_7_zhanel_toishybekova.com.models.requests.LoginRequest;
import application.exam_7_zhanel_toishybekova.com.services.AuthenticationService;
import application.exam_7_zhanel_toishybekova.com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerAPI
{
    private final UserService service;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<Void> registration(User user)
    {
        service.create(user);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Tokens> login(LoginRequest request)
    {
        Tokens tokens = authenticationService.authenticate(
                request.username(),
                request.password(),
                "/api/v1/users/login"
        );

        return ResponseEntity.ok(tokens);
    }
}
