package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @Getter
    @Column(nullable = false)
    private Integer age;
    @NonNull
    @Column(nullable = false)
    private String city;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
//    @Cascade(value = {CascadeType.DELETE})
//    List<Contact> contacts;

}
