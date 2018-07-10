/**
 * Created by Iryna on 01.07.2018.
 */
public class PersonSerializer {

    public String serialize(Person person) {
        return person.getIndex() + ", " + person.getSurname() + ", " + person.getName() + ", " + person.getAge() + ", " + person.getCity();
    }
}
