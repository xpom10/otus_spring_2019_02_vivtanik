package ru.otus.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.BookAuthor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("JpaQlInspection")
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BookAuthor getAuthorById(int id) {
        try {
            TypedQuery<BookAuthor> query = em.createQuery("select a from BookAuthor a where author_id = :id", BookAuthor.class);
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
            TypedQuery<BookAuthor> query = em.createQuery("select a from BookAuthor a where author_name = :name", BookAuthor.class);
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
    public long deleteAuthor(int id) {
        TypedQuery<Long> query = em.createQuery("delete from BookAuthor a where author_id = :id", Long.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
