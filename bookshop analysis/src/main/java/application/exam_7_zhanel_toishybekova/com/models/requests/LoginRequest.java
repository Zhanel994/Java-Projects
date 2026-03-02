package application.exam_7_zhanel_toishybekova.com.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for user login")
public record LoginRequest
(

        @Schema(description = "Username or email of the user", example = "user@example.com")
        String username,

        @Schema(description = "Password of the user", example = "strongPassword123")
        String password

) {}
