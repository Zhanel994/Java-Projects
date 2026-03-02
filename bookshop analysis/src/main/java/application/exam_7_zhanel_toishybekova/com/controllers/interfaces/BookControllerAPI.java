package application.exam_7_zhanel_toishybekova.com.controllers.interfaces;

import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/books")
@Tag(name = "Book Management", description = "Operations with book model")
public interface BookControllerAPI {
    @PostMapping
    @Operation (
                    summary = "Create a new book",
                    description = "Creating a new book",
                    responses =     {
                                    @ApiResponse(
                                                    responseCode = "202",
                                                    description = "Successful accepted",
                                                    useReturnTypeSchema = true
                                            )
                            }
            )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> create(@RequestBody BookDTO book);

    @GetMapping
    @Operation(
                    summary = "Retrieve a list of books",
                    description = "Retrieve a list of books DTO",
                    responses = {
                                    @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Success",
                                                    useReturnTypeSchema = true
                                            )
                            }
            )
    ResponseEntity<List<BookDTO>> getAll();

    @GetMapping("/{id}")
    @Operation (
                    summary = "Retrieve book",
                    description = "Retrieve concrete book by ID",
                    responses = {
                                    @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Success",
                                                    useReturnTypeSchema = true
                                            )
                            }
            )
    ResponseEntity<BookDTO> get(@PathVariable String id);


    @PutMapping("/{id}")
    @Operation(
                    summary = "Update book by ID",
                    description = "Update concrete book by ID",
                    responses = {
                                    @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Success",
                                                    useReturnTypeSchema = true
                                            )
                            }
            )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> update(@PathVariable String id, @RequestBody BookDTO book);

    @DeleteMapping("/{id}")
    @Operation
            (
                    summary = "Delete book",
                    description = "Delete concrete book by ID",
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

