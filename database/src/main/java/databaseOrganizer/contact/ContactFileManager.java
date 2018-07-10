package databaseOrganizer.contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactFileManager {

    private final static String CONTACT_FILE = "C:\\Users\\Iryna\\Desktop\\contactData.txt";

    public String readById(String wantedId) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(",");
                String id = line[0];
                if (id.equals(wantedId)) {
                    return currentLine;
                }
            }
        }
        return null;
    }

    public List<String> readByPersonId(String id) {
        List<String> linesOfPersonalDataBySurname = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
                String currentLine;
                try {
                    while ((currentLine = br.readLine()) != null) {
                        String[] line = currentLine.split(", ");
                        String surname = line[1];
                        if (surname.equals(id)) {
                            linesOfPersonalDataBySurname.add(currentLine);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed reading by person id");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesOfPersonalDataBySurname;
    }

    public void writeToFile(String transcription) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTACT_FILE, true))) {
            bufferedWriter.write(transcription);
            bufferedWriter.newLine();
        }
    }
    public void makeEmpty() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTACT_FILE, false))) {
            bufferedWriter.write("");
        }
    }
}
