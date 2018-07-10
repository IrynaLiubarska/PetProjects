package library.controller.command;

/**
 * Created by Iryna on 30.11.2015.
 */
public class CommandFactory {

    public Command createCommand(String requestType) {
        if ("author".equals(requestType)) {
            return new ShowAuthor();
        } else if ("book".equals(requestType)) {
            return new ShowBook();
        } else if ("bookList".equals(requestType)) {
            return new ShowBookList();
        } else if ("authorList".equals(requestType)) {
            return new ShowAuthorList();
        } else if ("newBook".equals(requestType)) {
            return new ShowNewBook();
        } else if ("newAuthor".equals(requestType)) {
            return new PostNewAuthor();
        } else if ("postNewBook".equals(requestType)) {
            return new PostNewBook();
        } else if ("search".equals(requestType)) {
            return new ShowSearch();
        }
        return null;
    }

}
