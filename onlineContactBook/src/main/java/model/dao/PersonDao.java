package model.dao;

import model.Person;

import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
public interface PersonDao extends Dao<Person> {

    List<Person> getBySurname(String surname);
}
