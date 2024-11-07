package infrastructure.adapters;

import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Deck;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemAdapter implements DeckPersistenceOutputPort {
    private static final String STORAGE_DIR = "flashcards_data";
    private static final String DECKS_FILE = "decks.dat";

    public FileSystemAdapter() {
        initializeStorage();
    }

    private void initializeStorage() {
        try {
            File directory = new File(STORAGE_DIR);
            if (!directory.exists()) {
                directory.mkdir();
            }
        } catch (Exception e) {
            System.out.println("Error initializing storage.");
        }
    }

    @Override
    public void saveDeck(Deck deck) {
        try {
            Map<String, Deck> decks = loadAllDecks();
            decks.put(deck.getId(), deck);
            saveAllDecks(decks);
        } catch (Exception e) {
            System.out.println("Error saving deck.");
        }
    }

    @Override
    public Deck loadDeck(String deckId) {
        try {
            return loadAllDecks().get(deckId);
        } catch (Exception e) {
            System.out.println("Error loading deck.");
            return null;
        }
    }

    @Override
    public List<Deck> getAllDecks() {
        try {
            return new ArrayList<>(loadAllDecks().values());
        } catch (Exception e) {
            System.out.println("Error loading decks.");
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteDeck(String deckId) {
        try {
            Map<String, Deck> decks = loadAllDecks();
            decks.remove(deckId);
            saveAllDecks(decks);
        } catch (Exception e) {
            System.out.println("Error deleting deck.");
        }
    }

    private Map<String, Deck> loadAllDecks() {
        File file = new File(STORAGE_DIR, DECKS_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, Deck>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveAllDecks(Map<String, Deck> decks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(STORAGE_DIR, DECKS_FILE)))) {
            oos.writeObject(decks);
        } catch (Exception e) {
            System.out.println("Error saving all decks.");
        }
    }
}