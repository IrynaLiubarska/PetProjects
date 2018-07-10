import java.io.*;
import java.util.*;

/**
 * Created by Iryna on 30.06.2018.
 */
public class PeopleOrganizer {

    private static String inputFile = "C:\\Users\\Iryna\\Desktop\\fileToRead.txt";
    private static String outputDirectory = "C:\\Users\\Iryna\\Desktop\\";
    private static Map<String, List<Person>> cityToPeopleList = new HashMap<>();
    private static PersonDeserializer personDeserializer = new PersonDeserializer();
    private static PersonSerializer personSerializer = new PersonSerializer();

    public static void main(String[] args) throws IOException {
        readFile();
        sortByAgeSurnameName();
        writeToFile();
    }

    private static void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                Person person = personDeserializer.deserialize(currentLine);
                String city = person.getCity();
                addPersonToCityToPeopleList(person, city);
            }
        }
    }

    private static void addPersonToCityToPeopleList(Person person, String city) {
        if (!cityToPeopleList.containsKey(city)) {
            List<Person> peopleList = new ArrayList<>();
            peopleList.add(person);
            cityToPeopleList.put(city, peopleList);
        } else {
            cityToPeopleList.get(city).add(person);
        }
    }

    private static void writeToFile() throws IOException {
        for (String city : cityToPeopleList.keySet()) {
            String fileName = createFileNameToWrite(city);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
                for (Person person : cityToPeopleList.get(city)) {
                    String personAsString = personSerializer.serialize(person);
                    bufferedWriter.write(personAsString);
                    bufferedWriter.newLine();
                }
            }
        }
    }

    private static String createFileNameToWrite(String city) {
        return outputDirectory + city + ".txt";
    }

    private static void peopleSortBy(Comparator<Person> kindOfComparator) {
        for (String city : cityToPeopleList.keySet()) {
            List<Person> peopleInCity = cityToPeopleList.get(city);
            Collections.sort(peopleInCity, kindOfComparator);
        }
    }

    private static void sortByAgeSurnameName() {
        peopleSortBy(new NameComparator());
        peopleSortBy(new SurnameComparator());
        peopleSortBy(new AgeComparator());
    }
}
