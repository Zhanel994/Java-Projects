package application.backend.com.repositories.specification;

import application.backend.com.entities.Trip;
import jakarta.persistence.criteria.Predicate;
import application.backend.com.entities.User;
import application.backend.com.filters.TripFilterParameters;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TripSpecification extends BaseSpecification<Trip> {

    public Specification<Trip> get(TripFilterParameters parameters) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            predicate = like(root, predicate, builder, parameters.getTitle(), "title");
            predicate = like(root, predicate, builder, parameters.getCity(), "city");
            predicate = like(root, predicate, builder, parameters.getDescription(), "description");
            return predicate;
        };
    }
}
