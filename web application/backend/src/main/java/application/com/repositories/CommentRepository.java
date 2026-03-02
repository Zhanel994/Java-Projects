package application.com.repositories;

import application.com.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    boolean existsCommentByPost_Id(String postId);

    List<Comment> getCommentsByPost_Id(String postId);

    void deleteAllByPost_Id(String postId);
}
