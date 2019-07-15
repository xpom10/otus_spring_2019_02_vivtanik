package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.services.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthorRestController {

    private final LibraryService libraryService;


    @GetMapping("/api/authors/{id}")
    public Mono<AuthorDto> getAuthor(@PathVariable String id) {
        return libraryService.findAuthor(id);
    }

    @GetMapping("/api/authors")
    public Flux<AuthorDto> getAllAuthors() {
        return libraryService.findAuthors();
    }


}
