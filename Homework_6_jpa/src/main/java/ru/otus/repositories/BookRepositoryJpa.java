package ru.otus.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book getBookById(long id) {
        try {
            TypedQuery<Book> query = em.createQuery("select b from Book b " +
                    "join fetch b.author a " +
                    "join fetch b.genre g " +
                    "where b.id = :id", Book.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
            query.setParameter("title", title);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public long createBook(Book book) {
        em.persist(book);
        return book.getId();
    }

    @Override
    public long deleteBookById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
