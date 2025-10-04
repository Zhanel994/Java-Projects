package application.backend.com.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static application.backend.com.utils.RegexConstants.EMAIL_PATTERN;
import static application.backend.com.utils.RegexConstants.PASSWORD_PATTERN;

@Schema(description = "Request body for user registration")
public record RegistrationRequest (
        @NotBlank(message = "Username cannot be empty!")
        @Size(max = 25)
        @Schema(description = "Username of the user", example = "Whisky")
        String username,

        @NotBlank(message = "User password cannot be empty!")
        @Size(max = 25)
        @Pattern(regexp =  PASSWORD_PATTERN, message = "Minimum eight characters, at least one letter and one number")
        @Schema(description = "Password of the user", example = "Whisky12345")
        String password,

        @NotBlank(message = "User email cannot be empty!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Not valid email")
        @Schema(description = "Email of the user", example = "username")
        String email,

        @Schema(description = "Name of the user", example = "Wisky")
        String name,

        @Schema(description = "Bio of the user", example = "3 years old. Likes fishes. Hard-working cat!")
        String bio,

        @Schema(description = "Phone of the user", example = "123-456-7890")
        String phone,

        @Schema(description = "Gender of the user", example = "male")
        String gender

){}
