package databaseOrganizer.delete;

import java.util.NoSuchElementException;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteFactory {

    public static Deleter createDeleter(DeletePolicy deletePolicy) {
        if (deletePolicy.equals(DeletePolicy.DELETE_RESTRICT)) {
            return new DeleteRestrict();
        } else if (deletePolicy.equals(DeletePolicy.DELETE_NO_ACTION)) {
            return new DeleteNoAction();
        } else if (deletePolicy.equals(DeletePolicy.DELETE_CASCADE)) {
            return new DeleteCascade();
        } else {
            throw new NoSuchElementException("there is no delete policy");
        }
    }

}
