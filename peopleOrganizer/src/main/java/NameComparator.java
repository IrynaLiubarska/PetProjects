import java.util.Comparator;

/**
 * Created by Iryna on 02.07.2018.
 */
public class NameComparator implements Comparator<Person> {
    
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getName().compareToIgnoreCase(person2.getName());
    }
}
