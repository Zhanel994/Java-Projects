package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.Book;
import application.exam_7_zhanel_toishybekova.com.entities.Category;
import application.exam_7_zhanel_toishybekova.com.repositories.BookRepository;
import application.exam_7_zhanel_toishybekova.com.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Iterable<Book> getAll() {
        return repository.findAll();
    }

    public Book get(String id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id %s not found!".formatted(id)));
    }

    public Optional<Book> getById(String id) {
        return repository.findById(id);
    }

    public void save(Book book) {
        validate(book);
        repository.save(book);
    }

    public void delete(String id)
    {
        repository.deleteById(id);
    }

    private void validate(Book book)
    {

        if (repository.existsByTitle(book.getTitle()))
        {
            throw new IllegalArgumentException("Book with this title is already exists!");
        }

        if (book.getTitle() == null || book.getTitle().isBlank())
        {
            throw new IllegalArgumentException("Book title is null or empty!");
        }
    }
}
