package ru.otus.repositories;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate template;

    @Override
    public long addComment(String title, Comment comment) {
        Query query = new Query(Criteria.where("title").is(title));
        Update update = new Update().addToSet("comments", comment);
        UpdateResult updateResult = template.updateFirst(query, update, Book.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public List<Genre> findGenres() {
        Aggregation aggregation = newAggregation(project().and("genre.genreName").as("genre_name"));
        return template.aggregate(aggregation, Book.class, Genre.class).getMappedResults()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
