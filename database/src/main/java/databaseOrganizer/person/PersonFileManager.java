package databaseOrganizer.person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonFileManager {

    private final static String PERSON_FILE = "C:\\Users\\Iryna\\Desktop\\personalData.txt";

    public String readById(Integer personId) {
        String finalResultOfCurrentLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(", ");
                String id = line[0];
                if (id.equals(Integer.toString(personId))) {
                    finalResultOfCurrentLine = currentLine;
                    if (line[1].equals("DELETE")) {
                        throw new RuntimeException("The person with this id was deleted");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(personId + "Failed to find this person id");
        }
        return finalResultOfCurrentLine;
    }

    public List<String> readBySurname(String wantedSurname) {
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
        } catch (IOException e) {
            throw new RuntimeException("Failed to get by surname");
        }
        return linesOfPersonalDataBySurname;
    }

    public void writeToFile(String transcription) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_FILE, true))) {
            bufferedWriter.write(transcription);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write in File");
        }
    }

    public void makeEmpty() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_FILE, false))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    public Integer readLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(", ");
                Integer id = Integer.parseInt(line[0]);
                if (id > max) {
                    max = id;
                }
            }
        } catch (IOException e) {
            // this is expected if database is empty (file does not exists)
        }
        return max;
    }
}
