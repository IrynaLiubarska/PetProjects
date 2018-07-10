package databaseOrganizer.delete;

import databaseOrganizer.person.PersonFileManager;

import java.io.IOException;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteNoAction implements Deleter {
    
    private PersonFileManager personFileManager = new PersonFileManager();

    @Override
    public void delete(Integer personId) {
        try {
            String line = personFileManager.readById(personId);
            if (line.isEmpty()) {
                throw new RuntimeException("The record is empty");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to get by id");
        }
        try {
            personFileManager.writeToFile("person with this person id is deleted " + Integer.toString(personId));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete person");
        }
    }
}
