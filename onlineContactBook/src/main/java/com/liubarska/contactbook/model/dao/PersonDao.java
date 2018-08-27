package com.liubarska.contactbook.model.dao;

import com.liubarska.contactbook.model.person.Person;

import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
public interface PersonDao extends Dao<Person> {

    List<Person> getBySurname(String surname);
}
