package application.exam_7_zhanel_toishybekova.com.mapper;

import application.exam_7_zhanel_toishybekova.com.entities.Book;
import application.exam_7_zhanel_toishybekova.com.models.DTO.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final CategoryMapper mapper;

    public Book toEntity(BookDTO bookDTO)
    {
       if (bookDTO == null)
           return null;

       Book book = new Book();
       book.setId(bookDTO.id());
       book.setTitle(bookDTO.title());
       book.setAuthor(bookDTO.author());
       book.setImage(bookDTO.image());
       book.setReleaseYear(bookDTO.releaseYear());
       book.setDescription(bookDTO.description());
       book.setCreatedAt(bookDTO.createdAt());
       book.setStatus(bookDTO.status());
       book.setCategory(mapper.toEntity(bookDTO.category()));

       return book;
    }

    public List<BookDTO> toResponse(List<Book> books)
    {
        return books
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookDTO toResponse(Book book)
    {
        if (book == null)
            return null;

        return new BookDTO
                (
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getImage(),
                        book.getReleaseYear(),
                        book.getDescription(),
                        book.getCreatedAt(),
                        book.getStatus(),
                        mapper.toResponse(book.getCategory())
                );
    }
}
