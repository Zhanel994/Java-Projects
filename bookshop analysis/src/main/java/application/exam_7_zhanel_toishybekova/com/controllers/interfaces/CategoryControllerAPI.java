package application.exam_7_zhanel_toishybekova.com.controllers.interfaces;

import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import application.exam_7_zhanel_toishybekova.com.models.DTO.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categories")
@Tag(name = "Category Management", description = "Operations with category model")
public interface CategoryControllerAPI {
    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Creating a new category",
            responses =     {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successful accepted",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> create(@RequestBody CategoryDTO category);

    @GetMapping
    @Operation(
            summary = "Retrieve a list of categories",
            description = "Retrieve a list of categories DTO",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<List<CategoryDTO>> getAll();

    @GetMapping("/{id}")
    @Operation (
            summary = "Retrieve category",
            description = "Retrieve concrete category by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<CategoryDTO> get(@PathVariable String id);


    @PutMapping("/{id}")
    @Operation(
            summary = "Update category by ID",
            description = "Update concrete category by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> update(@PathVariable String id, @RequestBody CategoryDTO category);

    @DeleteMapping("/{id}")
    @Operation
            (
                    summary = "Delete category",
                    description = "Delete concrete category by ID",
                    responses = {
                            @ApiResponse (
                                    responseCode = "200",
                                    description = "Success",
                                    useReturnTypeSchema = true
                            )
                    }
            )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> delete(@PathVariable String id);
}
