package ru.otus.repositories;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate template;

    @Override
    public Mono<Comment> addComment(String id, Comment comment) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().addToSet("comments", comment);

        Mono<UpdateResult> updateResult = template.updateFirst(query, update, Book.class);
        return updateResult.then(Mono.just(comment));
    }

    @Override
    public Flux<Genre> findGenres() {
        Aggregation aggregation = newAggregation(project().and("genre.genreName").as("genre_name"));
        return template.aggregate(aggregation, Book.class, Genre.class).distinct();
    }
}
