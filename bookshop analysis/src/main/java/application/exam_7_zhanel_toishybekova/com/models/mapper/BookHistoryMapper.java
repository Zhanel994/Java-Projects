package application.exam_7_zhanel_toishybekova.com.models.mapper;

import application.exam_7_zhanel_toishybekova.com.entities.BookHistory;
import application.exam_7_zhanel_toishybekova.com.models.response.BorrowResponse;
import org.springframework.stereotype.Component;

@Component
public class BookHistoryMapper {
    public BorrowResponse toResponse(BookHistory bookHistory) {
        return BorrowResponse.builder()
                .userId(bookHistory.getUser().getId())
                .bookId(bookHistory.getBook().getId())
                .takenAt(bookHistory.getTakenAt())
                .returnedAt(bookHistory.getReturnedAt())
                .build();
    }
}
