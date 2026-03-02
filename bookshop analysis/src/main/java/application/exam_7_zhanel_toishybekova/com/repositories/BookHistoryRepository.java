package application.exam_7_zhanel_toishybekova.com.repositories;

import application.exam_7_zhanel_toishybekova.com.entities.BookHistory;
import application.exam_7_zhanel_toishybekova.com.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookHistoryRepository extends CrudRepository<BookHistory, String> {
    List<BookHistory> findByUserUsernameAndReturnedAtIsNull(String username);
    boolean existsByBookIdAndReturnedAtIsNull(String bookId);
    List<BookHistory> findByUserId(String userId);

}
