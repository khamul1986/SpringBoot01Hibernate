package pl.khamul.SpringBoot01Hibernate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.khamul.SpringBoot01Hibernate.dao.PublisherDao;
import pl.khamul.SpringBoot01Hibernate.entity.Publisher;

@Controller
public class PublisherController {

    private final PublisherDao publisherDao;

    PublisherController(PublisherDao publisherDao){
        this.publisherDao = publisherDao;
    }

    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Kreator");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawcę:"
                + publisher.getId();
    }

    @GetMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @GetMapping("/publisher/update/{id}/{newName}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String newName) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(newName);
        publisherDao.update(publisher);
        return publisher.toString();
    }


    @GetMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "deleted";
    }
}
