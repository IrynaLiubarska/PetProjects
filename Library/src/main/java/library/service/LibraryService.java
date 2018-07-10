package library.service;

import library.model.Author;
import library.model.Book;

import java.util.Set;

public interface LibraryService {

    public static LibraryService instance = new DataBaseLibraryService();

    Set<Book> getAllBooks();

    Set<Author> getAllAuthors();

    Set<Author> getAuthorByName(String lastName);

    Book getBookById(int id);

    Author getAuthorById(int id);

    void addAuthor(Author author);

    void addBook(Book book);

    Set<Book> getSearchResultByManyValues(String name, int yearOfPrint, String isbn);

}
