package pl.khamul.SpringBoot01Hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Author;
import pl.khamul.SpringBoot01Hibernate.entity.Book;
import pl.khamul.SpringBoot01Hibernate.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book)); }


    public List<Book> findall() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :givenrating");
        query.setParameter("givenrating", rating);
        return query.getResultList();
    }

    public List<Book> findPublish() {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher IS NOT NULL");
        return query.getResultList();
    }

    public List<Book> findByPubl(Publisher publisher) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher");
        query.setParameter("publisher", publisher);
        return query.getResultList();
    }


    public List<Book> findByAuthor(Author author) {
        Query query = entityManager.createQuery("SELECT b FROM Book b INNER JOIN  b.authors a WHERE a.id = :authorId");
        query.setParameter("authorId", author.getId());
        return query.getResultList();
    }

}
