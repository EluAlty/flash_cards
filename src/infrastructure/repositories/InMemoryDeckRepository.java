// src/infrastructure/repositories/InMemoryDeckRepository.java
package infrastructure.repositories;

import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Deck;

import java.util.*;

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
    public void save(Deck deck) {
        decks.put(deck.getId(), deck);
    }

    @Override
    public Optional<Deck> findById(String id) {
        return Optional.ofNullable(decks.get(id));
    }

    @Override
    public List<Deck> findAll() {
        return new ArrayList<>(decks.values());
    }

    @Override
    public void delete(String id) {
        decks.remove(id);
    }
}