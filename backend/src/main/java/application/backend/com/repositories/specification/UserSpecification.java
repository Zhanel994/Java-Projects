package application.backend.com.repositories.specification;

import jakarta.persistence.criteria.Predicate;
import application.backend.com.entities.User;
import application.backend.com.filters.UserFilterParameters;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification extends BaseSpecification<User> {

    public Specification<User> get(UserFilterParameters parameters) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            predicate = like(root, predicate, builder, parameters.getUsername(), "username");
            predicate = like(root, predicate, builder, parameters.getEmail(), "email");
            predicate = like(root, predicate, builder, parameters.getName(), "name");
            predicate = like(root, predicate, builder, parameters.getBio(), "bio");

            return predicate;
        };
    }
}
