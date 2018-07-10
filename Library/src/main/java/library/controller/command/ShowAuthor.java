package library.controller.command;

import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAuthor extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        additionToRequestWithForwarding(LibraryService.instance.getAuthorById(Integer.parseInt(request.getParameter("authorId"))), "author", "author.jsp", request, response);
    }

}
