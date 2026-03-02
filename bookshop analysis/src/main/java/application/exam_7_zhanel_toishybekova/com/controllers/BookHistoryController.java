package application.exam_7_zhanel_toishybekova.com.controllers;

import application.exam_7_zhanel_toishybekova.com.controllers.interfaces.BookHistoryControllerAPI;
import application.exam_7_zhanel_toishybekova.com.models.requests.BorrowRequest;
import application.exam_7_zhanel_toishybekova.com.models.response.BorrowResponse;
import application.exam_7_zhanel_toishybekova.com.services.BookHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookHistoryController implements BookHistoryControllerAPI {
    private final BookHistoryService service;

    @Override
    public ResponseEntity<BorrowResponse> borrowBook(BorrowRequest request) {
        BorrowResponse response = service.borrowBook(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BorrowResponse> returnBook(String historyId) {
        BorrowResponse response = service.returnBook(historyId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Iterable<?>> getUserHistory(String userId) {
        return ResponseEntity.ok(service.getUserHistory(userId));
    }


}
