package infrastructure.repositories;

import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDeckRepository implements DeckPersistenceOutputPort {
    private static InMemoryDeckRepository instance;
    private final Map<String, Deck> decks = new HashMap<>();

    private InMemoryDeckRepository() {}

    public static InMemoryDeckRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryDeckRepository();
        }
        return instance;
    }

    @Override
    public void saveDeck(Deck deck) {
        decks.put(deck.getId(), deck);
    }

    @Override
    public Deck loadDeck(String deckId) {
        return decks.get(deckId);
    }

    @Override
    public void deleteDeck(String deckId) {
        decks.remove(deckId);
    }

    @Override
    public List<Deck> getAllDecks() {
        return new ArrayList<>(decks.values());
    }
}