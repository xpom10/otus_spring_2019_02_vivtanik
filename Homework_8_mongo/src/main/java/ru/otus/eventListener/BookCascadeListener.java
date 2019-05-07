package ru.otus.eventListener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookCascadeListener extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();
        if (book.getAuthor() != null) {
            Optional<Author> author = authorRepository.findAuthorByAuthorName(book.getAuthor().getAuthorName());
            if (!author.isPresent())
                authorRepository.save(book.getAuthor());
        }
    }



}
