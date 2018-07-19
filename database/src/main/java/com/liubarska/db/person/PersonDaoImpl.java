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
    private PersonSerializer personSerializer = new PersonSerializer();
    private PersonDeserializer personDeserializer = new PersonDeserializer();
    private Integer currentId;
    private DeleteStrategy deleteStrategy;

    public PersonDaoImpl() {
        this(DeletePolicy.DELETE_NO_ACTION, null);
    }

    public PersonDaoImpl(DeletePolicy deletePolicy, ContactDao contactDao) {
        deleteStrategy = DeleteStrategyFactory.create(deletePolicy, contactDao);
        currentId = personFileManager.readLargestId() + 1;
    }

    @Override
    public void insert(@NonNull Person person) {
        if (person.getId() == null) {
            String record = createRecord(person);
            personFileManager.writeToFile(record);
        } else {
            throw new IllegalArgumentException("person id should be null");
        }
    }

    @Override
    public Person getById(@NonNull Integer id) {
        String record = personFileManager.readById(id);
        if (record == null) {
            return null;
        }
        return personDeserializer.deserialize(record);
    }

    @Override
    public List<Person> getBySurname(@NonNull String surname) {
        List<String> persons = personFileManager.readBySurname(surname);
        return personDeserializer.deserialize(persons);
    }

    @Override
    public void deleteAll() {
        personFileManager.makeEmpty();
        currentId = 0;
    }

    @Override
    public void delete(@NonNull Integer id) {
        Person person = getById(id);
        if (person == null) {
            throw new RuntimeException("There is no such person"); 
        }
        deleteStrategy.delete(id);
        personFileManager.writeToFile(Integer.toString(id) + ", DELETE");
    }

    private String createRecord(Person person) {
        person.setId(currentId++);
        return personSerializer.serialize(person);
    }
}
