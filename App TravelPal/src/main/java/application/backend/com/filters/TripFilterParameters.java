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
public class TripFilterParameters extends PageParameters {

    @Schema(description = "Search by title", example = "Germany")
    private String title;

    @Schema(description = "Search by city", example = "Berlin")
    private String city;

    @Schema(description = "Search by description", example = "shopping")
    private String description;

    @Override
    @JsonCreator
    public void setSortBy(String value) {
        sortBy = BaseSort.valueOf(value);
    }
}
