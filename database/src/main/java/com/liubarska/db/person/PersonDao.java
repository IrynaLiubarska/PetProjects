package com.liubarska.db.person;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface PersonDao {

    void insert(Person person);

    Person getById(Integer id);

    List<Person> getBySurname(String surname);

    void deleteById(Integer personId);

    void deleteAll();
}
