package application.month9onlineshop.com.models.request;

import application.month9onlineshop.com.entities.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request for updating a product")
public record UpdateProductRequest(
        @NotNull
        @Schema(description = "Identifier of the product", example = "1")
        Long id,

        @NotBlank
        @Size(max = 100)
        @Schema(description = "Name of the product", example = "DELL XPS 15 9520")
        String name,

        @Size(max = 255)
        @Schema(description = "URL of the product image", example = "https://itmag.kz/upload/iblock/8/53/product_image_123053_1373541.jpg")
        String imageUrl,

        @NotNull
        @Positive
        @Schema(description = "Available quantity of the product", example = "30")
        Integer quantity,

        @NotBlank
        @Schema(description = "Description of the product", example = "Ultimate clarity. Experience the highest clarity and detail on a 15.6\" display with 3456x2160 resolution and touchscreen.")
        String description,

        @NotNull
        @Schema(description = "Price of the product", example = "1.799.990")
        BigDecimal price,

        @NotNull
        @Schema(description = "Category of the product", example = "LAPTOP")
        ProductCategory category
) {}
