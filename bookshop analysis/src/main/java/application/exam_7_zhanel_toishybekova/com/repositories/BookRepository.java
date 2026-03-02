package application.exam_7_zhanel_toishybekova.com.repositories;

import application.exam_7_zhanel_toishybekova.com.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
    boolean existsByTitle(String title);
}
