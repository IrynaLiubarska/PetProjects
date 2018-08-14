package model.person;

import lombok.*;
import model.contact.Contact;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
public class Person {
    @Setter
    @Id
    @GeneratedValue
    @Column(name = "person_id", nullable = false)
    private int id;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private String lastName;
    @NonNull
    @Column(nullable = false)
    private Integer age;
    @NonNull
    @Column(nullable = false) 
    private String city;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Contact> contacts;
}
