package databaseOrganizer.person;

/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonSerializer {
  
    public String serialize(Person person) {
        return person.getId() + ", " + person.getSurname() + ", " + person.getName() + ", " + person.getAge() + ", " + person.getCity();
    }
}
