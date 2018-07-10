package databaseOrganizer.contact;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactDeserializer {

    public Contact deserialize(String lineOfTranscription) {
        String[] contactTranscription = lineOfTranscription.split(", ");
        Integer primaryKey = Integer.parseInt(contactTranscription[0]);
        Integer foreignKey = Integer.parseInt(contactTranscription[1]);
        ContactType contactType = ContactType.valueOf(contactTranscription[2]);
        String value= contactTranscription[3];
        return new Contact(primaryKey, foreignKey, contactType, value);
    }
}
