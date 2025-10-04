package application.backend.com.filters.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseSort implements Sortable {
    ID("id");

    private final String column;
}
