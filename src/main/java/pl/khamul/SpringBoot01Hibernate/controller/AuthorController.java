package pl.khamul.SpringBoot01Hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.khamul.SpringBoot01Hibernate.dao.AuthorDao;

import pl.khamul.SpringBoot01Hibernate.entity.Author;


@Controller
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @RequestMapping("/author/add")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("Karate");
        author.setLastName("Mistrz");
        authorDao.saveAuthor(author);
        return "Id dodanego autora:"
                + author.getId();
    }

    @GetMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author= authorDao.findById(id);
        return author.toString();
    }

    @GetMapping("/author/update/{id}/{newName}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String newName) {
        Author author= authorDao.findById(id);
        author.setLastName(newName);
        authorDao.update(author);
        return author.toString();
    }


    @GetMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }
}