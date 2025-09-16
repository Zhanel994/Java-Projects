package application.month9onlineshop.com.models.request;

import application.month9onlineshop.com.entities.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "Request for creating a new product")
public record CreateProductRequest(

        @NotBlank
        @Size(max = 100)
        @Schema(description = "Name of the product", example = "iPhone 16 Pro")
        String name,

        @Size(max = 255)
        @Schema(description = "URL of the product image", example = "https://gadgetstore.kz/wa-data/public/shop/products/76/10/1076/images/2918/2918.970.jpg")
        String imageUrl,

        @NotNull
        @Positive
        @Schema(description = "Available quantity of the product", example = "50")
        Integer quantity,

        @NotBlank
        @Schema(description = "Description of the product", example = "iPhone 16 has a sturdy, aerospace-grade aluminum enclosure and strong — and beautiful — color-infused glass on the back.")
        String description,

        @NotNull
        @Schema(description = "Price of the product", example = "434.900")
        BigDecimal price,

        @NotNull
        @Schema(description = "Category of the product", example = "SMARTPHONE")
        ProductCategory category
) {}
