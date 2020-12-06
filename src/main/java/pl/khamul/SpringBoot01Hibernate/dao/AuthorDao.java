package pl.khamul.SpringBoot01Hibernate.dao;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Author;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class AuthorDao {


    @PersistenceContext
    EntityManager entityManager;

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author)); }

    public List<Author> findall() {
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        return query.getResultList();
    }
}
