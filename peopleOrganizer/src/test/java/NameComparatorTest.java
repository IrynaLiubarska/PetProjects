import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Iryna on 02.07.2018.
 */
public class NameComparatorTest {

    private NameComparator nameComparator = new NameComparator();

    @Test
    public void shouldCompareSameName() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarska", "Dmytro", 26, "Munich");
        assertEquals(0, nameComparator.compare(person1, person2));
    }

    @Test
    public void shouldCompareDifferentNameAndResultShouldBeLessThanZero() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarska", "Iryna", 26, "Munich");
        assertTrue(nameComparator.compare(person1, person2) < 0);
    }

    @Test
    public void shouldCompareDifferentNameAndResultShouldBeGreaterThanZero() {
        Person person1 = new Person(2, "Liubarska", "Iryna", 26, "Munich");
        Person person2 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        assertTrue(nameComparator.compare(person1, person2) > 0);
    }
}
