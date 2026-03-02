package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserService userService;
    private final TokenService tokenService;

    private final AuthenticationManager manager;

    public Tokens authenticate(String username, String password, String issuer)
    {
        User user = (User) userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
        (
                user,
                password,
                user.getAuthorities()
        );
        manager.authenticate(token);

        return tokenService.generate(username, issuer);
    }
}
