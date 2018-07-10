import java.util.Comparator;

/**
 * Created by Iryna on 01.07.2018.
 */
public class AgeComparator implements Comparator<Person> {
   
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getAge() - person2.getAge();
    }
}
