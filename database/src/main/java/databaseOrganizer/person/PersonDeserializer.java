package databaseOrganizer.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDeserializer {
    
    public Person deserialize(String lineOfTranscription) {
        String[] personTranscription = lineOfTranscription.split(", ");
        int id = Integer.parseInt(personTranscription[0]);
        String surname = personTranscription[1];
        String name = personTranscription[2];
        int age = Integer.parseInt(personTranscription[3]);
        String city = personTranscription[4];
        return new Person(id, surname, name, age, city);
    }

    public List<Person> convert(List<String> allLinesFromDatabase) {
        List<Person> personList = new ArrayList<>();
        for (String line : allLinesFromDatabase) {
            Person data = deserialize(line);
            personList.add(data);
        }
        return personList;
    }
}
