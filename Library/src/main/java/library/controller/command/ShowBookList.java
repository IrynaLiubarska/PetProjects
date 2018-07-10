package library.controller.command;

import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Iryna on 30.11.2015.
 */
public class ShowBookList extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        additionToRequestWithForwarding(LibraryService.instance.getAllBooks(), "books", "bookList.jsp", request, response);
    }

}
