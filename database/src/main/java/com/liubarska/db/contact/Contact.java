package com.liubarska.db.contact;

import lombok.*;

/**
 * Created by Iryna on 06.07.2018.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Contact {

    @Setter
    private Integer id;
    @NonNull
    private Integer personId;
    @NonNull
    private ContactType contactType;
    @NonNull
    private String value;
}

