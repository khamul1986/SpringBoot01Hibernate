package pl.khamul.SpringBoot01Hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Book;
import pl.khamul.SpringBoot01Hibernate.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void saveDetails(PersonDetails details) {
        entityManager.persist(details);
    }

    public void update(PersonDetails details) {
        entityManager.merge(details);
    }

    public void delete(PersonDetails details) {
        entityManager.remove(entityManager.contains(details) ?
                details : entityManager.merge(details)); }
}
