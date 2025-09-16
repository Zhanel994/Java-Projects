package application.month9onlineshop.com.validators;

import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRequest;
import application.month9onlineshop.com.repositories.ProductRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRequestValidator {

    private final ProductRepository repository;

    public void validate(CreateProductRequest request) {
        String name = request.name();
        validateExistsByName(name);
    }

    public void validate(UpdateProductRequest request) {
        String name = request.name();
        validateExistsByName(name);
    }

    private void validateExistsByName(String name) {
        boolean exists = repository.existsByName(name);

        if (exists) {
            throw new ValidationException("Product with name '" + name + "' already exists");
        }
    }
}
