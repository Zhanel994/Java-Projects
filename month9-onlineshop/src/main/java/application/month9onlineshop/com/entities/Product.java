package application.month9onlineshop.com.entities;

import application.month9onlineshop.com.utils.BaseEntity;
import jakarta.persistence.Column;

import java.math.BigDecimal;

import application.month9onlineshop.com.entities.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "category", nullable = false)
    private ProductCategory category;
}
