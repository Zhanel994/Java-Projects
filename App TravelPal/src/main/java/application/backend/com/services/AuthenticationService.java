package application.backend.com.services;

import application.backend.com.entities.User;
import application.backend.com.models.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final TokenService tokenService;

    private final AuthenticationManager manager;

    public Tokens authenticate(String username, String password) {
        User user = (User) userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user,
                password,
                user.getAuthorities()
        );
        manager.authenticate(token);

        return tokenService.generate(username);
    }
}
