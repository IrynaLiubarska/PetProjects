import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Iryna on 02.07.2018.
 */
public class SurnameComparatorTest {
    SurnameComparator surnameComparator = new SurnameComparator();

    @Test
    public void shouldCompareSameSurname() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarskyi", "Iryna", 26, "Munich");
        assertEquals(0, surnameComparator.compare(person1, person2));
    }
    @Test
    public void shouldCompareDifferentNameAndResultShouldBeLessThanZero() {
        Person person1 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        Person person2 = new Person(2, "Liubarska", "Iryna", 26, "Munich");
        assertTrue(surnameComparator.compare(person1, person2) > 0);
    }

    @Test
    public void shouldCompareDifferentNameAndResultShouldBeGreaterThanZero() {
        Person person1 = new Person(2, "Liubarska", "Iryna", 26, "Munich");
        Person person2 = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        assertTrue(surnameComparator.compare(person1, person2) < 0);
    }
}
