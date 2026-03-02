package application.backend.com.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import application.backend.com.models.Tokens;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.sasl.AuthenticationException;

@RequestMapping("/tokens")
@Tag(name = "Token Management", description = "Operations with JWT tokens")
public interface TokenControllerAPI {
    @PostMapping("/refresh")
    @Operation (
            summary = "Refresh access and refresh tokens",
            description = "Accepts refresh token in Authorization header and returns new pair of tokens",
            responses = {
                    @ApiResponse (
                            responseCode = "200",
                            description = "Tokens refreshed successfully",
                            useReturnTypeSchema = true
                    ),
            }
    )
    ResponseEntity<Tokens> refresh(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String header) throws AuthenticationException;
}
