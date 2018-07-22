package com.liubarska.db.person;

import com.liubarska.db.contact.ContactDao;
import com.liubarska.db.delete.DeletePolicy;
import com.liubarska.db.delete.DeleteStrategy;
import com.liubarska.db.delete.DeleteStrategyFactory;
import lombok.NonNull;

import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDaoImpl implements PersonDao {

    private PersonFileManager personFileManager = new PersonFileManager();
    private DeleteStrategy deleteStrategy;

    public PersonDaoImpl() {
        this(DeletePolicy.DELETE_NO_ACTION, null);
    }

    public PersonDaoImpl(DeletePolicy deletePolicy, ContactDao contactDao) {
        deleteStrategy = DeleteStrategyFactory.create(deletePolicy, contactDao);
    }

    @Override
    public void insert(@NonNull Person person) {
        if (person.getId() == null) {
            personFileManager.insert(person);
        } else {
            throw new IllegalArgumentException("Person id should be null");
        }
    }

    @Override
    public Person getById(@NonNull Integer id) {
        return personFileManager.getById(id);
    }

    @Override
    public List<Person> getBySurname(@NonNull String surname) {
        return personFileManager.getBySurname(surname);
    }

    @Override
    public void deleteById(@NonNull Integer id) {
        Person person = getById(id);
        if (person == null) {
            throw new RuntimeException("There is no person with such id");
        }
        deleteStrategy.delete(id);
        personFileManager.deleteById(id);
    }

    @Override
    public void deleteAll() {
        personFileManager.deleteAll();
    }
}
