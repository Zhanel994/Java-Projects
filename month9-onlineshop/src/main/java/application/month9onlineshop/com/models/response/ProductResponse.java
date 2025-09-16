package application.month9onlineshop.com.models.response;

import application.month9onlineshop.com.entities.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Response body for a product")
public record ProductResponse (
    @Schema(description = "Identifier of the product", example = "1")
    Long id,

    @Schema(description = "Name of the product", example = "Samsung Tab A9")
    String name,

    @Schema(description = "URL of the product image", example = "https://api.technodom.kz/f3/api/v1/images/planshet_samsung_tab_a9_128gb_graphite_sm_x216bzaeskz_274670_1.jpg")
    String imageUrl,

    @Schema(description = "Available quantity of the product", example = "15")
    Integer quantity,

    @Schema(description = "Description of the product", example = "The Samsung Galaxy Tab A9 has an 8.7-inch display with a resolution of 1340 x 800 pixels. This means that the pixel density is low and I can actually see pixels when reading.")
    String description,

    @Schema(description = "Price of the product", example = "119.890")
    BigDecimal price,

    @Schema(description = "Category of the product", example = "TABLET")
    ProductCategory category
) {}
