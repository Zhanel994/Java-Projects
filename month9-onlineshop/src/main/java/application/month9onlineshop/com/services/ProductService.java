package application.month9onlineshop.com.services;

import application.month9onlineshop.com.converter.ProductConverter;
import application.month9onlineshop.com.entities.Product;
import application.month9onlineshop.com.exceptions.NotFoundException;
import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRequest;
import application.month9onlineshop.com.models.response.ProductResponse;
import application.month9onlineshop.com.repositories.ProductRepository;
import application.month9onlineshop.com.validators.ProductRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductConverter converter;
    private final ProductRequestValidator validator;

    public void create(CreateProductRequest request) {

        validator.validate(request);

        Product product = converter.toEntity(request);
        repository.save(product);
    }

    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getById(Long id) {
        return toResponse(findById(id));
    }

    public void update(UpdateProductRequest request) {
        validator.validate(request);

        Product product = findById(request.id());

        Product updated = converter.merge(request, product);
        repository.save(updated);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Product findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found!"));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getQuantity(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
