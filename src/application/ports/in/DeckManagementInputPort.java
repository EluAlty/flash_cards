package application.ports.in;

import application.dto.DeckDto;
import application.dto.CardDto;

import java.util.List;

public interface DeckManagementInputPort {
    DeckDto createDeck(String name, String description);
    void addCardToDeck(String deckId, CardDto card);
    void removeCardFromDeck(String deckId, String cardId);
    void deleteDeck(String deckId);
    List<DeckDto> getAllDecks();
    DeckDto getDeckById(String id);
}