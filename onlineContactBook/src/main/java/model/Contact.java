package model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Iryna on 02.08.2018.
 */

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Getter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id", unique = true, nullable = false)
    private Integer id;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @NonNull
    @Column(nullable = false)
    private ContactType contactType;
    @NonNull
    @Column(nullable = false)
    private String value;
    
}
