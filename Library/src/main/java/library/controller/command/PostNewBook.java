package library.controller.command;

import library.model.Author;
import library.model.Book;
import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Iryna on 30.11.2015.
 */
public class PostNewBook extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String nameOfBook = request.getParameter("nameOfBook");
        String isbn = request.getParameter("isbn");
        String[] stringId = request.getParameterValues("authorsUserSelection");
        int year = Integer.parseInt(request.getParameter("year"));
        int[] ids = new int[stringId.length];
        for (int i = 0; i < stringId.length; i++) {
            ids[i] = Integer.parseInt(stringId[i]);
        }
        Set<Author> authors = new HashSet<Author>();
        for (int id : ids) {
            authors.add(LibraryService.instance.getAuthorById(id));
        }
        Book book = new Book(nameOfBook, isbn, authors);
        book.setYearOfPrint(year);
        LibraryService.instance.addBook(book);
        additionToRequestWithForwarding(book, "book", "book.jsp", request, response);
    }
}
