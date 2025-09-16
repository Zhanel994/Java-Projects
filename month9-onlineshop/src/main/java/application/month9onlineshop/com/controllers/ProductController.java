package application.month9onlineshop.com.controllers;

import application.month9onlineshop.com.controllers.interfaces.ProductControllerAPI;
import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRequest;
import application.month9onlineshop.com.models.response.ProductResponse;
import application.month9onlineshop.com.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerAPI {

    private final ProductService service;

    @Override
    public ResponseEntity<Void> create(CreateProductRequest request) {
        service.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        List<ProductResponse> products = service.getAll();
        return new PageImpl<>(products, pageable, products.size());
    }

    @Override
    public ResponseEntity<ProductResponse> getById(Long id) {
        ProductResponse product = service.getById(id);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Void> update(UpdateProductRequest request) {
        service.update(request);
        return ResponseEntity
                .accepted()
                .build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deleteById(id);
        return ResponseEntity
                .accepted()
                .build();
    }
}
