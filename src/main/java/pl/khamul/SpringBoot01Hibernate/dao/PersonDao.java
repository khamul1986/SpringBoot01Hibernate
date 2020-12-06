package pl.khamul.SpringBoot01Hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.khamul.SpringBoot01Hibernate.entity.Book;
import pl.khamul.SpringBoot01Hibernate.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDao {


    @PersistenceContext
    EntityManager entityManager;

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public void delete(Person person) {
        entityManager.remove(entityManager.contains(person) ?
                person : entityManager.merge(person));
    }
}