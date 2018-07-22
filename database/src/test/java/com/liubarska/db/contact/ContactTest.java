package com.liubarska.db.contact;

import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactType;
import org.junit.Test;

/**
 * Created by Iryna on 09.07.2018.
 */
public class ContactTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNullPersonId(){
        new Contact(null, ContactType.SKYPE, "liubarskyi");
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNulContactType(){
        new Contact(0, null, "liubarskyi");
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNullValue(){
        new Contact(0, ContactType.SKYPE, null);
    }
    
}
