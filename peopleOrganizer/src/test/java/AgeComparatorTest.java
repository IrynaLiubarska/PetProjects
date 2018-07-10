import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Iryna on 02.07.2018.
 */
public class AgeComparatorTest {

    private AgeComparator ageComparator = new AgeComparator();

    @Test
    public void shouldCompareSameAge() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarska", "Iryna", 26, "Munich");
        assertEquals(0, ageComparator.compare(person1, person2));
    }

    @Test
    public void shouldCompareDifferentAgeWhenFirstPersonIsOlder() {
        Person person1 = new Person(2, "Liubarska", "Iryna", 29, "Munich");
        Person person2 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        assertTrue(ageComparator.compare(person1, person2) > 0);
    }

    @Test
    public void shouldCompareDifferentAgeWhenSecondPersonIsOlder() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarska", "Iryna", 29, "Munich");
        assertTrue(ageComparator.compare(person1, person2) < 0);
    }
}
