package ru.otus.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;
import ru.otus.domain.Comment;

import java.util.List;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
@RunWith(SpringRunner.class)
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testGetComments() {
        List<Comment> list1 = commentRepository.getAllCommentForBook(1);
        Assert.assertEquals(1, list1.size());
        list1.forEach(comment -> Assert.assertTrue(comment.getComment().matches("Comment[1]")));

        List<Comment> list2 = commentRepository.getAllCommentForBook(2);
        Assert.assertEquals(2, list2.size());
        list2.forEach(comment -> Assert.assertTrue(comment.getComment().matches("Comment[23]")));

        List<Comment> list3 = commentRepository.getAllCommentForBook(3);
        Assert.assertEquals(0, list3.size());
    }

    @Test
    public void testCreateComment() {
        Comment comment = new Comment(new Book(1, "Book1", new BookAuthor("Author1"), new BookGenre("Genre1")), "Comment4");
        commentRepository.createComment(comment);

        List<Comment> list = commentRepository.getAllCommentForBook(1);
        Assert.assertEquals(2, list.size());
        list.forEach(commentEntity -> Assert.assertTrue(commentEntity.getComment().matches("Comment[14]")));
    }
}
