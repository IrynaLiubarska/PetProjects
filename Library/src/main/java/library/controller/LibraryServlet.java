package library.controller;

import library.controller.command.Command;
import library.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {

    private CommandFactory factory = new CommandFactory();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String requestType = request.getParameter("requestType");
        Command command = factory.createCommand(requestType);
        command.execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
