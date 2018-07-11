package databaseOrganizer.delete;

import databaseOrganizer.contact.ContactDao;

import java.util.NoSuchElementException;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteFactory {

    public static Deleter createDeleter(DeletePolicy deletePolicy, ContactDao contactDao) {
        if (deletePolicy.equals(DeletePolicy.DELETE_RESTRICT)) {
            return new DeleteRestrict(contactDao);
        } else if (deletePolicy.equals(DeletePolicy.DELETE_NO_ACTION)) {
            return new DeleteNoAction();
        } else if (deletePolicy.equals(DeletePolicy.DELETE_CASCADE)) {
            return new DeleteCascade(contactDao);
        } else {
            throw new NoSuchElementException("there is no delete policy");
        }
    }

}
