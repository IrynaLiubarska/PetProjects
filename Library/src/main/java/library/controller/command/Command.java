package library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    public abstract void execute(HttpServletRequest request, HttpServletResponse response);

    protected void additionToRequestWithForwarding(Object obj, String nameOfObj, String nameOfForwardingPage, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(nameOfObj, obj);
            request.getRequestDispatcher(nameOfForwardingPage).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
