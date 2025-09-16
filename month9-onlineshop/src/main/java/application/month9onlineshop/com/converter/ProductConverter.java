package application.month9onlineshop.com.converter;

import application.month9onlineshop.com.entities.Product;
import application.month9onlineshop.com.models.request.CreateProductRequest;
import application.month9onlineshop.com.models.request.UpdateProductRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public Product toEntity(CreateProductRequest request) {
        Product product = new Product();

        product.setName(request.name());

        product.setImageUrl(request.imageUrl());
        product.setQuantity(request.quantity());
        product.setDescription(request.description());
        product.setPrice(request.price());

        product.setCategory(request.category());

        return product;
    }

    public Product merge(UpdateProductRecord request, Product product) {
        product.setName(request.name());

        product.setImageUrl(request.imageUrl());
        product.setQuantity(request.quantity());
        product.setDescription(request.description());
        product.setPrice(request.price());

        product.setCategory(request.category());

        return product;
    }
}
