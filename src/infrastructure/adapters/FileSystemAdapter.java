package infrastructure.adapters;

import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Deck;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSystemAdapter implements DeckPersistenceOutputPort {
    private static final String STORAGE_DIR = "decks/";

    public FileSystemAdapter() {
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void saveDeck(Deck deck) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_DIR + deck.getId() + ".ser"))) {
            oos.writeObject(deck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Deck loadDeck(String deckId) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_DIR + deckId + ".ser"))) {
            return (Deck) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteDeck(String deckId) {
        File file = new File(STORAGE_DIR + deckId + ".ser");
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public List<Deck> getAllDecks() {
        List<Deck> decks = new ArrayList<>();
        File dir = new File(STORAGE_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Deck deck = (Deck) ois.readObject();
                    decks.add(deck);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return decks;
    }
}