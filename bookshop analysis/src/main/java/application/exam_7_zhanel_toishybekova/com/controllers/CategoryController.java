package application.exam_7_zhanel_toishybekova.com.controllers;

import application.exam_7_zhanel_toishybekova.com.controllers.interfaces.CategoryControllerAPI;
import application.exam_7_zhanel_toishybekova.com.entities.Book;
import application.exam_7_zhanel_toishybekova.com.entities.Category;
import application.exam_7_zhanel_toishybekova.com.mapper.CategoryMapper;
import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import application.exam_7_zhanel_toishybekova.com.models.DTO.CategoryDTO;
import application.exam_7_zhanel_toishybekova.com.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerAPI {
    private final CategoryService service;
    private final CategoryMapper mapper;

    @Override
    public ResponseEntity<Void> create(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        service.save(category);

        return ResponseEntity
                            .accepted()
                            .build();
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = (List<Category>) service.getAll();

        return ResponseEntity
                            .ok(mapper.toResponse(categories));
    }

    @Override
    public ResponseEntity<CategoryDTO> get(String id) {
        Category category = service.get(id);
        return ResponseEntity
                            .ok(mapper.toResponse(category));
    }

    @Override
    public ResponseEntity<Void> update(String id, CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        category.setId(id);
        service.save(category);
        return ResponseEntity
                            .accepted()
                            .build();
    }

    @Override
    public ResponseEntity<Void> delete(String id)
    {
        service.delete(id);

        return ResponseEntity
                            .accepted()
                            .build();
    }

}
