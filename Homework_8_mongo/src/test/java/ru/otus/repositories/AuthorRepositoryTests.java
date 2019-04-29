package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.List;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.config", "ru.otus.repositories"})
@RunWith(SpringRunner.class)
public class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void test() {
        List<Author> authors = authorRepository.findAll();
        System.out.println(authors);
    }
}
