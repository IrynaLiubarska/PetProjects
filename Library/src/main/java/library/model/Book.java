package library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "yearOfPrint")
    private int yearOfPrint;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_to_author", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    public Book() {

    }

    public Book(String name, String isbn, Set<Author> authors) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
    }

    public Book(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setYearOfPrint(int yearOfPrint) {
        this.yearOfPrint = yearOfPrint;
    }

    public int getYearOfPrint() {
        return this.yearOfPrint;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Author> getAllAuthors() {
        return new HashSet<>(this.authors);
    }

}
