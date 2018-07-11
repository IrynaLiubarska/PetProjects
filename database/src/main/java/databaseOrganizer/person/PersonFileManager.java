package databaseOrganizer.person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonFileManager {

    private final static String PERSON_FILE = "C:\\Users\\Iryna\\Desktop\\personalData.txt";

    public String readById(Integer wantedId) throws IOException {
        String finalResultOfCurrentLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(", ");
                String id = line[0];
                if (id.equals(Integer.toString(wantedId))) {
                    finalResultOfCurrentLine = currentLine;
                    if(line[1].equals("DEL")){
                        throw new RuntimeException("The person with this id was deleted");
                    }
                    // return currentLine;
                }
            }

        }
        return finalResultOfCurrentLine;
    }

    public List<String> readBySurname(String wantedSurname) throws IOException {
        List<String> linesOfPersonalDataBySurname = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(", ");
                String surname = line[1];
                if (surname.equals(wantedSurname)) {
                    linesOfPersonalDataBySurname.add(currentLine);
                }
            }
        }
        return linesOfPersonalDataBySurname;
    }

    public void writeToFile(String transcription) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_FILE, true))) {
            bufferedWriter.write(transcription);
            bufferedWriter.newLine();
        }
    }

    public void makeEmpty() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_FILE, false))) {
            bufferedWriter.write("");
        }
    }
//
//    public Integer readLargestId(Integer counter) throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
//            String currentLine;
//            while ((currentLine = br.readLine()) != null) {
//                String[] line = currentLine.split(", ");
//                Integer id = Integer.parseInt(line[0]);
//                if (id > counter) {
//                    counter = id;
//                }
//            }
//        }
//        return counter;
//    }
}
