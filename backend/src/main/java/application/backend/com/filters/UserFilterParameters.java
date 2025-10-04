package application.backend.com.filters;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import application.backend.com.filters.pageable.PageParameters;
import application.backend.com.filters.sort.BaseSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Filter parameters for searching users")
public class UserFilterParameters extends PageParameters {

    @Schema(description = "Search by username", example = "Whisky")
    private String username;

    @Schema(description = "Search by email", example = "whisky@mail.com")
    private String email;

    @Schema(description = "Search by name", example = "Whisky")
    private String name;

    @Schema(description = "Search by bio", example = "Hard-working cat!")
    private String bio;

    @Override
    @JsonCreator
    public void setSortBy(String value) {
        sortBy = BaseSort.valueOf(value);
    }
}
