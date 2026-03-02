package application.exam_7_zhanel_toishybekova.com.models.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BorrowResponse(
        String id,
        String userId,
        String bookId,
        String bookTitle,
        String username,
        LocalDateTime takenAt,
        LocalDateTime returnedAt
) {
}
