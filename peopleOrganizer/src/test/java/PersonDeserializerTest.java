import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 02.07.2018.
 */
public class PersonDeserializerTest {
    
    private PersonDeserializer personDeserializer = new PersonDeserializer();
    
    @Test
    public void shouldDeserialize() {
        assertEquals(new Person(1, "Liubarskyi", "Dmytro", 26, "Munich"), personDeserializer.deserialize("1,Liubarskyi,Dmytro,26,Munich"));
    }
}
