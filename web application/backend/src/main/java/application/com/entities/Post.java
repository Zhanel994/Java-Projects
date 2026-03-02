package application.com.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {

    @Id
    private String id = UUID.randomUUID().toString();

    private String topic;
    private String post;

    public Post(String topic, String post){

        this.topic = topic;
        this.post = post;
    }

}
