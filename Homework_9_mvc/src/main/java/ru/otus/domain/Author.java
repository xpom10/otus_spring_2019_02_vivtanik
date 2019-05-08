package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    @Field("author_name")
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public static Author fromDto(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.id);
        author.setAuthorName(authorDto.authorName);
        return author;
    }

    public static AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.id);
        authorDto.setAuthorName(author.authorName);
        return authorDto;
    }
}
