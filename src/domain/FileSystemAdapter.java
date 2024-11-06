package domain;

import java.io.*;
import java.util.List;

public class FileSystemAdapter {
    private static FileSystemAdapter instance;
    private final String basePath = "flashcards/";

    private FileSystemAdapter() {
        new File(basePath).mkdirs();
    }

    public static FileSystemAdapter getInstance() {
        if (instance == null) {
            instance = new FileSystemAdapter();
        }
        return instance;
    }

    public void saveToFile(String filename, List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(basePath + filename))) {
            data.forEach(writer::println);
        } catch (IOException e) {
            throw new RuntimeException("Error saving to file: " + filename, e);
        }
    }

    public List<String> readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(basePath + filename))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filename, e);
        }
    }
} 