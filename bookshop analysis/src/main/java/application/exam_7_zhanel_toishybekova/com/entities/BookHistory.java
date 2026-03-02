package application.exam_7_zhanel_toishybekova.com.entities;

import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookHistory {
    @Id
    private String id = UUID.randomUUID().toString();

    private User user;
    private Book book;
    private LocalDateTime takenAt;
    private LocalDateTime returnedAt;
}
