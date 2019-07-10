package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.domain.mongo.Author;
import ru.otus.domain.mongo.Book;
import ru.otus.domain.mongo.Genre;

@Profile("dev")
@ChangeLog(order = "001")
public class MongoChangeLog {

    Author pushkin = new Author("Пушкин");
    Author tolstoy = new Author("Толстой");


    private Book capitan = new Book(
            "Капитанская дочка",
            new Genre("Роман"),
            pushkin
    );

    private Book gypsies = new Book(
            "Цыганы",
            new Genre("Поэма"),
            pushkin
    );

    private Book warAndPeace = new Book(
            "Кавказский пленник",
            new Genre("Художественное произведение"),
            tolstoy
    );

    @ChangeSet(author = "vivtanikmv", id = "drop", order = "000", runAlways = true)
    public void dropDatabase(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(author = "vivtanikmv", id = "init_authors", order = "001", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        template.save(pushkin);
        template.save(tolstoy);
    }

    @ChangeSet(author = "vivtanikmv", id = "init_books", order = "002", runAlways = true)
    public void initBooks(MongoTemplate template) {
        template.save(capitan);
        template.save(warAndPeace);
        template.save(gypsies);
    }

}
