package com.liubarska.contactbook.model.person;

import lombok.*;
import com.liubarska.contactbook.model.contact.Contact;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
public class Person {
    
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
    @Setter
    private List<Contact> contacts;

    public Person(String name, String lastName, int age, String city, List<Contact> contacts) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.contacts = contacts;
    }
}
