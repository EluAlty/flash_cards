package application.ports.out;

import domain.entities.Deck;
import java.util.List;

public interface DeckPersistenceOutputPort {
    void saveDeck(Deck deck);
    Deck loadDeck(String deckId);
    void deleteDeck(String deckId);
    List<Deck> getAllDecks();
}