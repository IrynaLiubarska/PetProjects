import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Person {

    @NonNull
    private int index;
    @NonNull
    private String surname;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private String city;
    
}
