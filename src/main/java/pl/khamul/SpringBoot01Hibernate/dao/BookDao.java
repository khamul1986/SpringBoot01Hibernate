package pl.khamul.SpringBoot01Hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


}
