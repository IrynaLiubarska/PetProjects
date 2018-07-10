package library.service;

import library.model.Author;
import library.model.Book;

import java.util.Set;

public class DataBaseLibraryService implements LibraryService {

    private BookDao bookDao = new BookDao();
    private AuthorDao authorDao = new AuthorDao();

    @Override
    public Set<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Set<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @Override
    public Set<Author> getAuthorByName(String lastName) {
        return authorDao.getAuthorByName(lastName);
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Override
    public Author getAuthorById(int id) {
        return authorDao.getAuthorById(id);
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.addAuthor(author);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Set<Book> getSearchResultByManyValues(String name, int yearOfPrint, String isbn) {
        return bookDao.getSearchResultByManyValues(name, yearOfPrint, isbn);
    }
}
