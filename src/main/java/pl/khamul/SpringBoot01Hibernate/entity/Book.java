package pl.khamul.SpringBoot01Hibernate.entity;



import javax.persistence.*;


@Entity
@Table(name = " books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private int rating;

    private String descrtiption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrtiption() {
        return descrtiption;
    }

    public void setDescrtiption(String author) {
        this.descrtiption = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", descrtiption='" + descrtiption + '\'' +
                '}';
    }
}
