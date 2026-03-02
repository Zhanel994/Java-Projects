package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.Tokens;
import application.exam_7_zhanel_toishybekova.com.models.enums.TokenType;
import application.exam_7_zhanel_toishybekova.com.repositories.UserRepository;
import application.exam_7_zhanel_toishybekova.com.utils.PathRequestMatcher;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
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
public class TokenService
{
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    private final UserRepository repository;

    public TokenService(@Value("${application.jwt.signature.secret}") String secret, UserRepository repository)
    {
        algorithm = Algorithm.HMAC256(secret.getBytes());
        verifier = JWT
                .require(algorithm)
                .build();

        this.repository = repository;
    }

    public void verify(HttpServletRequest request) throws IOException
    {
        if (!PathRequestMatcher.matches(request))
        {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            authorize(header);
        }
    }

    private void authorize(String header) throws IOException
    {
        validate(header);

        DecodedJWT decodedJWT = getDecodedJWT(header);

        String username = decodedJWT.getSubject();
        User user = loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
        (
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public Tokens refresh(String header, String issuer) throws AuthenticationException
    {
        validate(header);

        DecodedJWT decodedJWT = getDecodedJWT(header);

        String username = decodedJWT.getSubject();
        return generate(username, issuer);
    }

    public Tokens generate(String user, String issuer)
    {
        return new Tokens
        (
                generate(user, issuer, TokenType.ACCESS),
                generate(user, issuer, TokenType.REFRESH)
        );
    }

    private DecodedJWT getDecodedJWT(String header)
    {
        String bearer = header.substring("Bearer ".length());
        return verifier.verify(bearer);
    }

    private String generate(String username, String issuer, TokenType type)
    {
        User user = loadUserByUsername(username);

        Date expires = switch (type)
        {
            case ACCESS -> new Date(System.currentTimeMillis() + 60 * 1000);        // 1 min
            case REFRESH -> new Date(System.currentTimeMillis() + 30 * 60 * 1000);  // 30 min
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
                .withExpiresAt(expires)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    private void validate(String header) throws AuthenticationException {
        if (header == null)
        {
            throw new AuthenticationException("Token is null!");
        }

        if (!header.startsWith("Bearer "))
        {

            throw new AuthenticationException("Token is not Bearer!");
        }
    }

    private User loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return repository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
