package ru.otus.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.BookAuthor;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("JpaQlInspection")
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BookAuthor getAuthorById(long id) {
        try {
            TypedQuery<BookAuthor> query = em.createQuery("select a from BookAuthor a where a.authorBookId = :id", BookAuthor.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<BookAuthor> getAuthors() {
        TypedQuery<BookAuthor> query = em.createQuery("select a from BookAuthor a", BookAuthor.class);
        return query.getResultList();
    }

    @Override
    public BookAuthor getAuthorByName(String name) {
        try {
            TypedQuery<BookAuthor> query = em.createQuery("select a from BookAuthor a where a.authorName = :name", BookAuthor.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public long createAuthor(BookAuthor author) {
        em.persist(author);
        return author.getAuthorBookId();
    }

    @Override
    public long deleteAuthor(long id) {
        Query query = em.createQuery("delete from BookAuthor a where a.authorBookId = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
