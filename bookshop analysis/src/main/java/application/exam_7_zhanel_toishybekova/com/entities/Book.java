package application.exam_7_zhanel_toishybekova.com.entities;

import application.exam_7_zhanel_toishybekova.com.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id = UUID.randomUUID().toString();

    private String title;
    private String author;
    private String image;
    private int releaseYear;
    private String description;
    private LocalDateTime createdAt;

    private Status status = Status.AVAILABLE;

    private Category category;
}
