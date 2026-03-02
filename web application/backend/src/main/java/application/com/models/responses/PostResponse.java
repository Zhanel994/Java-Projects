package application.com.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Post response")
public record PostResponse(

        @Schema(description = "Post id", example = "e8a5f9e6-4c4e-4c89-97c1-a1e7288d4781")
        @JsonProperty("postId")
        String postId,

        @Schema(description = "Post topic", example = "New Born kitten won’t eat. Help!!")
        @JsonProperty("topic")
        String topic,

        @Schema(description = "Post body", example = "Hi, I would appreciate some help! Long story short, " +
                "my cat had 3 kittens two were unfortunately born sleeping and one was alive and well! " +
                "He will be 6 weeks ...")
        @JsonProperty("post")
        String Post

    ){}
