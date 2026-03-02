
package application.com.services;

import application.com.entities.Comment;
import application.com.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public List<Comment> getByPost(String postId){
        if (!repository.existsCommentByPost_Id(postId)){
            throw new IllegalArgumentException("No one comment for posts ID:" + postId + " found");
        }
        return repository.getCommentsByPost_Id(postId);
    }


}
