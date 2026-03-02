package application.com.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="comment response")
public record CommentResponse(

    @Schema(description = "Comment id", example = "e8a5f9e6-4c4e-4c89-97c1-a1e7288d4781")
    @JsonProperty("commentId")
    String commentId,

    @Schema(description = "Comment body", example = "If the kitten is healthy, I’d just leave them alone for now. He/she may be a little slower developing than others.")
    @JsonProperty("comment")
    String comment

    ){}
