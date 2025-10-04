package application.backend.com.filters.sort;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface SortableCriteria
{
    void setSortBy(String value);

    Sortable getSortByOrDefault();

    @JsonIgnore
    default Sort getSort()
    {
        return Sort.by(getDirection(), getSortByOrDefault().getColumn());
    }

    default Direction getDirection()
    {
        return Direction.DESC;
    }
}
