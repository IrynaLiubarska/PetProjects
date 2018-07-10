package library.controller.command;

import library.model.Author;
import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Iryna on 30.11.2015.
 */
public class PostNewAuthor extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Author author = new Author(request.getParameter("lastName"), request.getParameter("firstName"));
        LibraryService.instance.addAuthor(author);
        additionToRequestWithForwarding(author, "author", "author.jsp", request, response);
    }

}
