package ru.otus.repositories.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import ru.otus.domain.mongo.Book;
import ru.otus.domain.mongo.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate template;

    @Override
    public List<Genre> findGenres() {
        Aggregation aggregation = newAggregation(project().and("genre.genreName").as("genre_name"));
        return template.aggregate(aggregation, Book.class, Genre.class).getMappedResults()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
