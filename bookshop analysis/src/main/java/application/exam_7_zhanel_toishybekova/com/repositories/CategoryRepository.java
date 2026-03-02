package application.exam_7_zhanel_toishybekova.com.repositories;

import application.exam_7_zhanel_toishybekova.com.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    boolean existsByName(String name);
}
