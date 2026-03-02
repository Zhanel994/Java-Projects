package application.backend.com.filters.pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import application.backend.com.filters.sort.BaseSort;
import application.backend.com.filters.sort.Sortable;
import application.backend.com.filters.sort.SortableCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Page parameters for sorting and pagination")
public class PageParameters implements SortableCriteria {
    @NotNull
    @Min(1)
    @Schema(description = "Page number", example = "1")
    protected Integer number = 1;

    @NotNull
    @Min(1)
    @Max(100)
    @Schema(description = "Page size", example = "100")
    protected Integer size = 100;

    @Schema(description = "Sorting column", example = "ID", type = "string")
    protected Sortable sortBy;

    @NotNull
    @Schema(description = "Sorting direction", example = "DESC")
    protected Direction direction;

    @Override
    @JsonCreator
    public void setSortBy(String value) {
        sortBy = BaseSort.valueOf(value);
    }

    @Override
    @JsonIgnore
    public Sortable getSortByOrDefault() {
        return (sortBy == null) ? BaseSort.ID : sortBy;
    }

    @JsonIgnore
    public PageRequest getPageRequest() {
        PageRequest request = PageRequest.of(number - 1, size);

        if (getSortByOrDefault() != null) {
            request = request.withSort(Sort.by(direction, getSortBy().getColumn()));
        }

        return request;
    }

}
