package databaseOrganizer.person;

import databaseOrganizer.contact.ContactDao;
import databaseOrganizer.delete.DeleteFactory;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.delete.Deleter;
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
    private Deleter deleter;

    public PersonDaoImpl(DeletePolicy deletePolicy, ContactDao contactDao) {
        deleter = DeleteFactory.createDeleter(deletePolicy, contactDao);
        currentId = personFileManager.readLargestId() + 1;        
    }

    @Override
    public void insert(@NonNull Person person) {
        if (person.getId() == null) {
            String record = createRecordLine(person);
            personFileManager.writeToFile(record);
        } else {
            throw new IllegalArgumentException("person id should be null");
        }
    }

    @Override
    public Person getById(@NonNull Integer id) {
        String line = personFileManager.readById(id);
        if (line.isEmpty()) {
            throw new RuntimeException("The record is empty");
        }
        return personDeserializer.deserialize(line);
    }

    @Override
    public List<Person> getBySurname(@NonNull String surname) {
        List<String> transcriptionsListWithSameSurname = personFileManager.readBySurname(surname);
        return personDeserializer.convert(transcriptionsListWithSameSurname);
    }

    @Override
    public void deleteAll() {
        personFileManager.makeEmpty();
        currentId = 0;
    }

    @Override
    public void delete(Integer personId) {
        deleter.delete(personId);
        deletePerson(personId);
    }

    private void deletePerson(Integer personId) {
        personFileManager.readById(personId);
        personFileManager.writeToFile(Integer.toString(personId) + ", DELETE");
    }

    private String createRecordLine(Person person) {
        person.setId(currentId++);
        return personSerializer.serialize(person);
    }

}
