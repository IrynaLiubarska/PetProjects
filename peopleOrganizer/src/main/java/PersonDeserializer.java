/**
 * Created by Iryna on 01.07.2018.
 */
public class PersonDeserializer {
    public Person deserialize(String lineOfTranscription) {
        String[] personTranscription = lineOfTranscription.split(",");
        int index = Integer.parseInt(personTranscription[0]);
        String surname = personTranscription[1];
        String name = personTranscription[2];
        int age = Integer.parseInt(personTranscription[3]);
        String city = personTranscription[4];
        return new Person(index, surname, name, age, city);
    }
}
