package pl.khamul.SpringBoot01Hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.khamul.SpringBoot01Hibernate.dao.PersonDao;
import pl.khamul.SpringBoot01Hibernate.dao.PersonDetailsDao;
import pl.khamul.SpringBoot01Hibernate.entity.Person;
import pl.khamul.SpringBoot01Hibernate.entity.PersonDetails;


@Controller
public class PersonController {

    private final PersonDao personDao;
    private final PersonDetailsDao detailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao detailsDao) {
        this.personDao = personDao;
        this.detailsDao = detailsDao;
    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String hello() {
        Person person = new Person();
        person.setLogin("raz");
        person.setEmail("raz@raz.raz");
        person.setPassword("razraz");


        PersonDetails details = new PersonDetails();



        person.setDetails(details);
        personDao.savePerson(person);
        return "Id dodanego autora:"
                + person.getId();
    }

    @GetMapping("/person/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @GetMapping("/person/update/{id}/{newName}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String newName) {
        Person person = personDao.findById(id);

        return person.toString();
    }


    @GetMapping("/person/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "deleted";
    }
}
