package pl.khamul.SpringBoot01Hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Book;
import pl.khamul.SpringBoot01Hibernate.entity.Publisher;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher)); }

    public List<Publisher> findall() {
        Query query = entityManager.createQuery("SELECT p FROM Publisher p");
        return query.getResultList();
    }
}
