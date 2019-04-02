package ru.otus.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.BookGenre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("JpaQlInspection")
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BookGenre getGenreById(int id) {
        try {
            TypedQuery<BookGenre> query = em.createQuery("select g from BookGenre g where book_genre_id = :id", BookGenre.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<BookGenre> getGenres() {
        TypedQuery<BookGenre> query = em.createQuery("select g from BookGenre g", BookGenre.class);
        return query.getResultList();
    }

    @Override
    public BookGenre getGenreByName(String name) {
        try {
            TypedQuery<BookGenre> query = em.createQuery("select g from BookGenre g where genre = :genre", BookGenre.class);
            query.setParameter("genre", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public long createGenre(String genre) {
        BookGenre bookGenre = new BookGenre(genre);
        em.persist(bookGenre);
        return bookGenre.getBookGenreId();
    }
}
