package databaseOrganizer.contact;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface ContactDao {

    void insert(Contact contact);

    List<Contact> getByPersonId(Integer personId);

    void deleteAll();

    void deleteById(Integer id);

    void deleteByPersonId(Integer personId);
}
