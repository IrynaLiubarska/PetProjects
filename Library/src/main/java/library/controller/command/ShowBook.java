package library.controller.command;

import library.model.Book;
import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBook extends Command {
    private Book book;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        book = LibraryService.instance.getBookById(Integer.parseInt(request.getParameter("bookId")));
        additionToRequestWithForwarding(book, "book", "book.jsp", request, response);
    }
}
