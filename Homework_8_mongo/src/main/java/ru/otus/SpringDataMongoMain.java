package ru.otus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.domain.BookAuthor;
import ru.otus.repositories.AuthorRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringDataMongoMain {

    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongoMain.class, args);
    }

    @PostConstruct
    public void create() {
        BookAuthor author = new BookAuthor("asd");
        authorRepository.save(author);
    }


}
