package databaseOrganizer.person;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface PersonDao {
    
    void insert(Person person);

    Person getById(Integer id);

    List<Person> getBySurname(String surname);
    
    void deleteAll();
    
    void delete(Integer personId);
    
}
