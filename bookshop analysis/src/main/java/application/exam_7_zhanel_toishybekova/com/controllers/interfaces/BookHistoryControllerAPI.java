package application.exam_7_zhanel_toishybekova.com.controllers.interfaces;

import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import application.exam_7_zhanel_toishybekova.com.models.requests.BorrowRequest;
import application.exam_7_zhanel_toishybekova.com.models.response.BorrowResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/book-histories")
@Tag(name = "Book Management", description = "Operations with book model")
public interface BookHistoryControllerAPI {
    @PostMapping("/borrow")
    @Operation(
            summary = "Borrow book",
            description = "Borrowing book",
            responses =     {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successful accepted",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<BorrowResponse> borrowBook(@RequestBody BorrowRequest request);

    @PostMapping("/return/{historyId}")
    @Operation(
            summary = "Returning book",
            description = "Returning borrowed book",
            responses =     {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successful accepted",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<BorrowResponse> returnBook(@PathVariable String historyId);

    @GetMapping
    @Operation(
            summary = "All books",
            description = "Returning borrowed book",
            responses =     {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successful accepted",
                            useReturnTypeSchema = true
                    )
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Iterable<?>> getUserHistory(String userId);

}
