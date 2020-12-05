package pl.khamul.SpringBoot01Hibernate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.khamul.SpringBoot01Hibernate.dao.AuthorDao;
import pl.khamul.SpringBoot01Hibernate.dao.BookDao;
import pl.khamul.SpringBoot01Hibernate.dao.PublisherDao;
import pl.khamul.SpringBoot01Hibernate.entity.Author;
import pl.khamul.SpringBoot01Hibernate.entity.Book;
import pl.khamul.SpringBoot01Hibernate.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao)
    {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setAuthor("Bruce Eckel");
        book.setDescription("abc");
        book.setRating(3);

        Publisher publisher = new Publisher();
        publisherDao.savePublisher(publisher);
        publisher.setName("PeWuEn");
        book.setPublisher(publisher);

        List<Author> authors = new ArrayList<>();
        Author author1 = authorDao.findById(1);
        Author author2 = authorDao.findById(2);
        authors.add(author1);
        authors.add(author2);
        book.setAuthors(authors);


        bookDao.saveBook(book);



        return "Id dodanej książki to:"
                + book.getId();
    }

    @GetMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @GetMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }


    @GetMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }
}
