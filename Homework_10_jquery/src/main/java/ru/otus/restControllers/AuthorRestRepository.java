package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthorRestRepository {

    private final AuthorRepository authorRepository;


    @GetMapping("/api/authors/{id}")
    public AuthorDto getAuthor(@PathVariable String id) {
        Optional<Author> author = authorRepository.findById(id);
        return Author.toDto(author.orElse(new Author()));
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(Author::toDto).collect(Collectors.toList());
    }


}
