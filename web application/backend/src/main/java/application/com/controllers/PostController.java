package application.com.controllers;

import application.com.controllers.interfaces.PostControllerAPI;
import application.com.entities.Comment;
import application.com.entities.Post;
import application.com.mappers.CommentMapper;
import application.com.mappers.PostMapper;
import application.com.models.responses.CommentResponse;
import application.com.models.responses.PostResponse;
import application.com.services.CommentService;
import application.com.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerAPI {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<List<PostResponse>> getPosts() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok(postMapper.toResponse(posts));
    }

    @Override
    public ResponseEntity<List<CommentResponse>> getComments(String postId) {
        List<Comment> comments = commentService.getByPost(postId);
        return ResponseEntity.ok(commentMapper.toResponse(comments));
    }

}
