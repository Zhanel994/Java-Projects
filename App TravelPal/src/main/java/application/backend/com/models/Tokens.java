package application.backend.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication tokens returned after login")
public record Tokens(
        @Schema(description = "Access token used for authorization", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        @JsonProperty("access_token")
        String accessToken,

        @Schema(description = "Refresh token used to obtain a new access token", example = "dGhpc0lzUmVmcmVzaFRva2Vu...")
        @JsonProperty("refresh_token")
        String refreshToken
) {}
