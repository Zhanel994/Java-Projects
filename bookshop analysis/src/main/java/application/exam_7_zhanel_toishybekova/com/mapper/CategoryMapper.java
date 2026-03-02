package application.exam_7_zhanel_toishybekova.com.mapper;

import application.exam_7_zhanel_toishybekova.com.entities.Category;
import application.exam_7_zhanel_toishybekova.com.models.DTO.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDTO category) {
        return new Category (
                        category.id(),
                        category.name()
        );
    }

    public List<CategoryDTO> toResponse(List<Category> categories) {
        return categories
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryDTO toResponse(Category category) {
        return new CategoryDTO (
                        category.getId(),
                        category.getName()
        );
    }
}
