package application.com.mappers;

import application.com.entities.Post;
import application.com.models.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {

    public PostResponse toResponse(Post post){
        return new PostResponse(
                post.getId(),
                post.getTopic(),
                post.getPost()
        );
    }

    public List<PostResponse> toResponse(List<Post> posts){
        return posts
                .stream()
                .map(this::toResponse)
                .toList();
    }

}
