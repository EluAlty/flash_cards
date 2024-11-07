package application.ports.in;

import application.dto.CardDto;
import application.dto.DeckDto;
import java.util.List;

public interface DeckManagementInputPort {
    DeckDto createDeck(String name, String description);
    void addCard(String deckId, CardDto cardDto);
    void removeCard(String deckId, String cardId);
    DeckDto getDeck(String deckId);
    List<DeckDto> getAllDecks();
    void deleteDeck(String deckId);
    void studyDeck(String deckId);
}