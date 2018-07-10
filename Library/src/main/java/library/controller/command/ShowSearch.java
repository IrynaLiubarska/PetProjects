package library.controller.command;

import library.service.LibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Iryna on 01.12.2015.
 */
public class ShowSearch extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String yearOfPrint = request.getParameter("yearOfPrint");
        int year;
        if (yearOfPrint != null && !yearOfPrint.isEmpty()) {
            year = Integer.parseInt(yearOfPrint);
        } else {
            year = 0;
        }
        String isbn = request.getParameter("isbn");
        additionToRequestWithForwarding(LibraryService.instance.getSearchResultByManyValues(name, year, isbn), "books", "result.jsp", request, response);
    }
}
