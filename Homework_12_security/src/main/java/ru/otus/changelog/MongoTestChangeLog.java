package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.security.domain.User;

import java.util.Arrays;
import java.util.Collections;

@Profile("test")
@ChangeLog(order = "001")
public class MongoTestChangeLog {

    Author author1 = new Author("author1");
    Author author2 = new Author("author2");


    private Book book1 = new Book(
            "book1",
            new Genre("genre1"),
            author1,
            Arrays.asList(new Comment("book1comment1"), new Comment("book1comment2"))
    );

    private Book book2 = new Book(
            "book2",
            new Genre("genre2"),
            author1,
            Collections.singletonList(new Comment("book2comment1"))
    );

    private Book book3 = new Book(
            "book3",
            new Genre("genre1"),
            author2,
            Collections.singletonList(new Comment("book3comment1"))
    );

    @ChangeSet(author = "vivtanikmv", id = "drop", order = "000", runAlways = true)
    public void dropDatabase(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(author = "vivtanikmv", id = "init_users", order = "001", runAlways = true)
    public void initUser(MongoTemplate template) {
        template.save(new User("admin", "password", "ADMIN"));
    }

    @ChangeSet(author = "vivtanikmv", id = "init_authors", order = "002", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        template.save(author1);
        template.save(author2);
    }

    @ChangeSet(author = "vivtanikmv", id = "init_books", order = "003", runAlways = true)
    public void initBooks(MongoTemplate template) {
        template.save(book1);
        template.save(book3);
        template.save(book2);
    }
}
