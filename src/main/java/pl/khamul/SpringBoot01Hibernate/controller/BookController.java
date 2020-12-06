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
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @GetMapping("/book/findall")
    @ResponseBody
    public String findall(){
        List<Book> allBooks = bookDao.findall();
        return allBooks.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping("/book/find/{rating}")
    @ResponseBody
    public String findByRating(@PathVariable int rating) {
        List<Book> booksByRating = bookDao.findByRating(rating);
        return booksByRating.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping("/book/find/publ")
    @ResponseBody
    public String findIfPubl(){
        List<Book> list = bookDao.findPublish();
        return  list.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br>"));
    }



    @GetMapping("/book/find/publ/{id}")
    @ResponseBody
    public String findByPubl(@PathVariable int id) {
        Publisher publisher = publisherDao.findById(id);
        List<Book> booksPubl = bookDao.findByPubl(publisher);
        return booksPubl.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br>"));
    }
    @GetMapping("/book/find/auth/{id}")
    @ResponseBody
    public String findbyAuthor(@PathVariable int id){
        Author author = authorDao.findById(id);
        List<Book> booksAuth = bookDao.findByAuthor(author);
        return booksAuth.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br>"));
    }
}
