package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.dto.CommentDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Field("comment")
    private String comment;

    public static Comment fromDto(CommentDto commentDto) {
        return new Comment(commentDto.comment);
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.comment);
    }

}
