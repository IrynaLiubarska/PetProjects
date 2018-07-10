package databaseOrganizer.person;

import databaseOrganizer.delete.DeleteFactory;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.delete.Deleter;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDaoImpl implements PersonDao {

    private PersonFileManager personFileManager = new PersonFileManager();
    private PersonSerializer personSerializer = new PersonSerializer();
    private PersonDeserializer personDeserializer = new PersonDeserializer();
    private int counter = 0; // TODO read from file on startup
    private Deleter deleter;

    public PersonDaoImpl(DeletePolicy deletePolicy){
        deleter = DeleteFactory.createDeleter(deletePolicy);
    }
    
    @Override
    public void insert(@NonNull Person person) {
        if (person.getId() == null) {
            String record = createRecordLine(person);
            try {
                personFileManager.writeToFile(record);
            } catch (IOException e) {
                throw new RuntimeException("Failed to insert");
            }
        }else{
            throw new IllegalArgumentException("person id should be null");
        }
    }

    @Override
    public Person getById(@NonNull Integer id) {
        try {
            String line = personFileManager.readById(id);
            if (line.isEmpty()) {
                throw new RuntimeException("The record is empty");
            }
            return personDeserializer.deserialize(line);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get by id");
        }
    }

    @Override
    public List<Person> getBySurname(@NonNull String surname) {
        try {
            List<String> transcriptionsListWithSameSurname = personFileManager.readBySurname(surname);
            return personDeserializer.convert(transcriptionsListWithSameSurname);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get by surname");
        }
    }

    @Override
    public void removeAll() {
        try {
            personFileManager.makeEmpty();
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    @Override
    public void delete(Integer personId) {
        deleter.delete(personId);

        // remove person from file
    }
    
    private void removePerson(Integer personId){
        try {
            personFileManager.writeToFile(Integer.toString(personId) + ", DELETE");
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete person");
        }
    }

    private String createRecordLine(Person person) {
        person.setId(counter++);
        return personSerializer.serialize(person);
    }

}
