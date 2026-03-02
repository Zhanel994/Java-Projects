package application.com.controllers.interfaces;


import application.com.models.responses.CommentResponse;
import application.com.models.responses.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/posts")
@Tag(name = "Post management", description = "Operation with post and comment model")
public interface PostControllerAPI {

    @GetMapping
    @Operation(
            summary = "Retrieve list of posts",
            description = "Retrieve list of posts",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            useReturnTypeSchema = true
                    )
            }
    )
    ResponseEntity<List<PostResponse>> getPosts();

    @GetMapping("/{postId}/comments")
    @Operation(
            summary = "Get comments for post",
            description = "Get comments by postId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            useReturnTypeSchema = true
                    )
            }
    )
    ResponseEntity<List<CommentResponse>> getComments(@PathVariable String postId);


}
