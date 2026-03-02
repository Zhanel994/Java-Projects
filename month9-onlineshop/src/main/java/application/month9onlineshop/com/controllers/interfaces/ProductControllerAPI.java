package application.month9onlineshop.com.controllers.interfaces;

import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRequest;
import application.month9onlineshop.com.models.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@Tag(name = "Product Management", description = "Create, update, retrieve and delete products")
public interface ProductControllerAPI {

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with its details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully")
            }
    )
    ResponseEntity<Void> create(@RequestBody @Valid CreateProductRequest request);

    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Retrieves a list of all products with pagination",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of products returned successfully",
                            useReturnTypeSchema = true
                    )
            }
    )
    Page<ProductResponse> getAll(@Parameter(hidden = true) Pageable pageable);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Retrieves a product by its unique identifier",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product returned successfully",
                            useReturnTypeSchema = true
                    )
            }
    )
    ResponseEntity<ProductResponse> getById(@PathVariable Long id);

    @PutMapping
    @Operation(
            summary = "Update product details",
            description = "Updates the details of an existing product",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Product update accepted"
                    )
            }
    )
    ResponseEntity<Void> update(@RequestBody @Valid UpdateProductRequest request);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete product by ID",
            description = "Deletes a product by its unique identifier",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Product deletion accepted"
                    )
            }
    )
    ResponseEntity<Void> delete(@PathVariable Long id);
}
