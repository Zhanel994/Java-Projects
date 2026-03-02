package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.Book;
import application.exam_7_zhanel_toishybekova.com.entities.BookHistory;
import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.enums.Status;
import application.exam_7_zhanel_toishybekova.com.models.mapper.BookHistoryMapper;
import application.exam_7_zhanel_toishybekova.com.models.requests.BorrowRequest;
import application.exam_7_zhanel_toishybekova.com.models.response.BorrowResponse;
import application.exam_7_zhanel_toishybekova.com.repositories.BookHistoryRepository;
import application.exam_7_zhanel_toishybekova.com.repositories.BookRepository;
import application.exam_7_zhanel_toishybekova.com.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookHistoryService {
    private final BookHistoryRepository bookHistoryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookHistoryMapper mapper;

    public BorrowResponse borrowBook(BorrowRequest request) {
        User user = userRepository
                                .findByUsername(request.username())
                                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Book book = bookRepository
                                    .findById(request.bookId())
                                    .orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        if(bookHistoryRepository.findByUserUsernameAndReturnedAtIsNull(user.getUsername()).size() >= 3) {
            throw new IllegalArgumentException("You already have 3 borrowed books");
        }

        if(bookHistoryRepository.existsByBookIdAndReturnedAtIsNull(book.getId())) {
            throw new IllegalArgumentException("This book is already borrowed");
        }
        book.setStatus(Status.BORROWED);

        BookHistory bookHistory = new BookHistory();
        bookHistory.setUser(user);
        bookHistory.setBook(book);
        bookHistory.setTakenAt(LocalDateTime.now());
        bookHistory.setReturnedAt(null);

        BookHistory saved = bookHistoryRepository.save(bookHistory);
        return mapper.toResponse(saved);
    }

    public BorrowResponse returnBook(String historyId) {
       BookHistory history = bookHistoryRepository.findById(historyId).orElseThrow(() -> new IllegalArgumentException("Book history not found!"));
       history.setReturnedAt(LocalDateTime.now());

       BookHistory updated = bookHistoryRepository.save(history);
       return mapper.toResponse(updated);
    }

    public Iterable<BookHistory> getUserHistory(String userId) {
        return bookHistoryRepository.findByUserId(userId);
    }
}
