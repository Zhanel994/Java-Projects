package application.exam_7_zhanel_toishybekova.com.models.DTO;

import application.exam_7_zhanel_toishybekova.com.models.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data transfer object for Book entity")
public record BookDTO(
        @Schema(description = "Unique ID of the book", example = "30061a7d-c96d-45fb-97ba-132980a77a3a")
        String id,

        @Schema(description = "Title of the book", example = "1984")
        String title,

        @Schema(description = "Author of the book", example = "George Orwell")
        String author,

        @Schema(description = "Image of the book", example = "https://citaty.info/files/posters/84337.jpg")
        String image,

        @Schema(description = "Year of the book", example = "1949")
        int releaseYear,

        @Schema(description = "Description of the book", example = "It tells the story of Winston Smith, a citizen of the miserable society of Oceania, who is trying to rebel against the Party and its omnipresent symbol, Big Brother.")
        String description,

        @Schema(description = "Created date", example = "2025-08-09")
        LocalDateTime createdAt,

        @Schema(description = "Status of the book", example = "AVAILABLE")
        Status status,

        @Schema(description = "Category of the book", example = "Science-fiction")
        CategoryDTO category
) {
}
