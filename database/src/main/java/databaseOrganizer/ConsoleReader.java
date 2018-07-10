package databaseOrganizer;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactType;
import databaseOrganizer.person.Person;

import java.util.Scanner;

/**
 * Created by Iryna on 03.07.2018.
 */
class ConsoleReader {

    private String readLine(String textToPrintInConsole) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter user " + textToPrintInConsole);
        String line = in.nextLine();
        return line;
    }

    public Person readPerson() {
        String surname = readLine("surname: ");
        String name = readLine("name: ");
        int age = Integer.parseInt(readLine("age: "));
        String city = readLine("city: ");
        return new Person(surname, name, age, city);
    }

    public Contact readContact() {
        int personId = Integer.parseInt(readLine("person id: "));
        ContactType contactType = ContactType.E_MAIL.valueOf(readLine("contact type: "));
        String value = readLine("value: ");
        return new Contact(personId, contactType, value);
    }
}
