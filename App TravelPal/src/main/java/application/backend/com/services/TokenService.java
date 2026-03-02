package application.backend.com.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import application.backend.com.entities.User;
import application.backend.com.entities.enums.TokenType;
import application.backend.com.models.Tokens;
import application.backend.com.repositories.UserRepository;
import application.backend.com.utils.PathRequestMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    private final UserRepository repository;

    public TokenService(@Value("${application.jwt.signature.secret}") String secret, UserRepository repository) {
        algorithm = Algorithm.HMAC256(secret.getBytes());
        verifier = JWT
                .require(algorithm)
                .build();

        this.repository = repository;
    }

    public void verify(HttpServletRequest request) throws IOException {
        if (!PathRequestMatcher.matches(request)) {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            authorize(header);
        }
    }

    private void authorize(String header) throws IOException {
        validate(header);

        DecodedJWT decodedJWT = getDecodedJWT(header);
        TokenType type = decodedJWT
                                .getClaim("type")
                                .as(TokenType.class);

        if (type != TokenType.ACCESS) {
            throw new AuthenticationException("Not access token!");
        }

        String username = decodedJWT.getSubject();
        User user = loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public Tokens refresh(String header) throws AuthenticationException {
        validate(header);

        DecodedJWT decodedJWT = getDecodedJWT(header);

        String username = decodedJWT.getSubject();
        TokenType type = decodedJWT
                                .getClaim("type")
                                .as(TokenType.class);

        if (type != TokenType.REFRESH) {
            throw new AuthenticationException("Not refresh token!");
        }

        return generate(username);
    }

    public Tokens generate(String user) {
        return new Tokens (
                generate(user, TokenType.ACCESS),
                generate(user, TokenType.REFRESH)
        );
    }

    private DecodedJWT getDecodedJWT(String header) {
        String bearer = header.substring("Bearer ".length());
        return verifier.verify(bearer);
    }

    private String generate(String username, TokenType type) {
        User user = loadUserByUsername(username);

        Date expires = switch (type) {
            case ACCESS -> new Date(System.currentTimeMillis() + 15 * 60 * 1000);
            case REFRESH -> new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        };

        List<String> authorities = user
                                        .getAuthorities()
                                        .stream()
                                        .map(Object::toString)
                                        .toList();
        return JWT
                .create()
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withClaim("type", type.toString())
                .withExpiresAt(expires)
                .withIssuer("shop application")
                .sign(algorithm);
    }

    private void validate(String header) throws AuthenticationException {
        if (header == null) {
            throw new AuthenticationException("Token is null!");
        }

        if (!header.startsWith("Bearer ")) {

            throw new AuthenticationException("Token is not Bearer!");
        }
    }

    private User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
