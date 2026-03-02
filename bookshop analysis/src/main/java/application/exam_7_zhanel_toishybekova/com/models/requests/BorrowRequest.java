package application.exam_7_zhanel_toishybekova.com.models.requests;

import java.util.UUID;

public record BorrowRequest(
        String bookId,
        String username
) {
}
