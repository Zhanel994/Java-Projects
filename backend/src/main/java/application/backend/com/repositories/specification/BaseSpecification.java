package application.backend.com.repositories.specification;

import jakarta.persistence.criteria.*;
import jakarta.validation.ValidationException;

public abstract class BaseSpecification<T> {
    protected Predicate equal(Root<T> root, Predicate predicate, CriteriaBuilder builder, Object field, String name) {
        if (field != null)
        {
            predicate = builder.and(predicate, builder.equal(root.get(name), field));
        }

        return predicate;
    }

    protected Predicate equal(
            Root<T> root,
            Predicate predicate,
            CriteriaBuilder builder,
            Object field,
            String name,
            String entityName
    )
    {
        if (field != null) {
            Join<T, ?> join = root.join(entityName);
            predicate = builder.and(predicate, builder.equal(join.get(name), field));
        }

        return predicate;
    }

    protected <Y extends Comparable<? super Y>> Predicate between(
            Root<T> root,
            Predicate predicate,
            CriteriaBuilder builder,
            Y from,
            Y to,
            String name
    )
    {
        if (from == null || to == null) {
            return predicate;
        }

        if (from.compareTo(to) > 0) {
            throw new ValidationException("'from' value cannot be greater than 'to' value.");
        }

        return builder.and(predicate, builder.between(root.get(name), from, to));
    }


    protected Predicate like(Root<T> root, Predicate predicate, CriteriaBuilder builder, String field, String name) {
        if (field == null || field.isBlank()) {
            return predicate;
        }

        String pattern = "%" + field.toLowerCase() + "%";
        Expression<String> lower = builder.lower(root.get(name));
        Predicate like = builder.like(lower, pattern);

        return builder.and(predicate, like);
    }
}
