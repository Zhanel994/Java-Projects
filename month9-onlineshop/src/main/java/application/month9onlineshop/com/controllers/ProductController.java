package application.month9onlineshop.com.controller;

import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRequest;
import application.month9onlineshop.com.models.response.ProductResponse;
import application.month9onlineshop.com.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductResponse> products = service.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductResponse product = service.getById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateProductRequest request) {
        service.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

