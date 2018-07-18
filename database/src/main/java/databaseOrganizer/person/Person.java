package databaseOrganizer.person;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Person {

    @Setter
    private Integer id;
    @NonNull
    private String surname;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private String city;
}
