package com.liubarska.db;

import java.io.*;

import static com.liubarska.db.Constants.*;

/**
 * Created by Iryna on 22.07.2018.
 */
public abstract class FileManager {

    private String file;
    protected int currentId;

    public FileManager(String file) {
        this.file = file;
        currentId = findLargestId() + 1;
    }

    private int findLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(FIELD_SEPARATOR);
                Integer id = Integer.parseInt(line[0]);
                max = getMax(max, id);
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return max;
    }

    private int getMax(int max, Integer id) {
        if (id > max) {
            max = id;
        }
        return max;
    }

    protected String doGetById(int id) {
        String record = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentRecord;
            while ((currentRecord = br.readLine()) != null) {
                String[] fields = currentRecord.split(FIELD_SEPARATOR);
                if (fields[0].equals(String.valueOf(id))) {
                    record = currentRecord;
                    if (fields[1].equals(TOMBSTONE)) {
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return record;
    }

    protected void writeToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }

    public void deleteById(Integer id) {
        writeToFile(Integer.toString(id) + FIELD_SEPARATOR + TOMBSTONE);
    }

    public void deleteAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write("");
            currentId = 0;
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }
}
