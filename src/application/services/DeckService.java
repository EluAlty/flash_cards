package application.services;

import domain.entities.Card;
import domain.entities.Deck;
import domain.repositories.DeckRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DeckService {
    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }



    public Deck createDeck(String name, String description) {
        Deck deck = new Deck.Builder()
                .name(name)
                .description(description)
                .build();
        deckRepository.save(deck);
        return deck;
    }

    public void addCardToDeck(String deckId, Card card) {
        deckRepository.findById(deckId).ifPresent(deck -> {
            deck.addCard(card);
            deckRepository.save(deck);
        });
    }

    public void removeCardFromDeck(String deckId, String cardId) {
        deckRepository.findById(deckId).ifPresent(deck -> {
            deck.removeCard(cardId);
            deckRepository.save(deck);
        });
    }

    public void deleteDeck(String deckId) {
        deckRepository.delete(deckId);
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Optional<Deck> getDeckById(String id) {
        return deckRepository.findById(id);
    }

    public void addCard(String deckName, String question, String answer, int difficulty) {
        Card card = new Card.Builder()
                .id(UUID.randomUUID().toString())
                .question(question)
                .answer(answer)
                .difficulty(difficulty)
                .build();
                
        getDeckById(deckName).ifPresent(deck -> {
            deck.addCard(card);
            deckRepository.save(deck);
        });
    }

    public void removeCard(String deckName, String cardId) {
        getDeckById(deckName).ifPresent(deck -> {
            deck.removeCard(cardId);
            deckRepository.save(deck);
        });
    }

    public Deck createDeck(String name) {
        return createDeck(name, "");
    }
} 