package databaseOrganizer.contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactFileManager {

    private final static String CONTACT_FILE = "C:\\Users\\Iryna\\Desktop\\contactData.txt";

    public String readById(Integer wantedId) {
        String finalResultOfCurrentLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            try {
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    String[] line = currentLine.split(",");
                    String id = line[0];
                    if (id.equals(wantedId.toString())) {
                        finalResultOfCurrentLine = currentLine;
                        if (line[1].equals("DEL")) {
                            throw new RuntimeException("The contact with this id was deleted");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(wantedId + "Failed reading by id");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not connect to file");
        }
        return finalResultOfCurrentLine;
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

    public void writeToFile(String transcription) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTACT_FILE, true))) {
            bufferedWriter.write(transcription);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write in File");
        }
    }

    public void makeEmpty() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTACT_FILE, false))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    public Integer readLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
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
