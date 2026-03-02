package application.exam_7_zhanel_toishybekova.com.controllers;

import application.exam_7_zhanel_toishybekova.com.controllers.interfaces.BookControllerAPI;
import application.exam_7_zhanel_toishybekova.com.entities.Book;
import application.exam_7_zhanel_toishybekova.com.mapper.BookMapper;
import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import application.exam_7_zhanel_toishybekova.com.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookControllerAPI {
    private final BookService service;
    private final BookMapper mapper;

    @Override
    public ResponseEntity<Void> create(BookDTO bookDTO) {
        Book book = mapper.toEntity(bookDTO);
        service.save(book);

        return ResponseEntity
                                .accepted()
                                .build();
    }

    @Override
    public ResponseEntity<List<BookDTO>> getAll() {
        List<Book> books = (List<Book>) service.getAll();

        return ResponseEntity
                            .ok(mapper.toResponse(books));
    }

    @Override
    public ResponseEntity<BookDTO> get(String id) {
        Book book = service.get(id);
        return ResponseEntity
                            .ok(mapper.toResponse(book));
    }

    @Override
    public ResponseEntity<Void> update(String id, BookDTO bookDTO) {
        Book book = mapper.toEntity(bookDTO);
        book.setId(id);
        service.save(book);
        return ResponseEntity
                            .accepted()
                            .build();
    }

    @Override
    public ResponseEntity<Void> delete(String id)
    {
        service.delete(id);

        return ResponseEntity
                            .accepted()
                            .build();
    }


}
