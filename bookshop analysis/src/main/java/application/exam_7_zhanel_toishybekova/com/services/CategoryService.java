package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.Category;
import application.exam_7_zhanel_toishybekova.com.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Iterable<Category> getAll() {
        return repository.findAll();
    }

    public Category get(String id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id %s not found!".formatted(id)));
    }

    public Optional<Category> getById(String id) {
        return repository.findById(id);
    }

    public void save(Category category) {
        validate(category);
        repository.save(category);
    }

    public void delete(String id)
    {
        repository.deleteById(id);
    }

    private void validate(Category category)
    {

        if (repository.existsByName(category.getName()))
        {
            throw new IllegalArgumentException("Category with this title is already exists!");
        }

        if (category.getName() == null || category.getName().isBlank())
        {
            throw new IllegalArgumentException("Category title is null or empty!");
        }
    }
}
