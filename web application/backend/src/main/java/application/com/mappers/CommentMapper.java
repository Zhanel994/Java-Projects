package application.com.mappers;

import application.com.entities.Comment;
import application.com.models.responses.CommentResponse;
import application.com.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    PostService postService;

    private CommentResponse toResponse(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getComment()
        );
    }

    public List<CommentResponse> toResponse(List<Comment> comments){
        return comments
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
