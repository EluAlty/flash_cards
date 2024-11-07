package presentation.commands;

import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import java.util.List;

public class ViewDecksCommand implements Command {
    private final DeckManagementInputPort deckService;

    public ViewDecksCommand(DeckManagementInputPort deckService) {
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        try {
            List<DeckDto> decks = deckService.getAllDecks();
            if (decks.isEmpty()) {
                System.out.println("No decks available.");
                return;
            }

            System.out.println("Available decks:");
            for (DeckDto deck : decks) {
                DeckDto fullDeck = deckService.getDeck(deck.getId());
                int cardCount = fullDeck.getCards() != null ? fullDeck.getCards().size() : 0;
                System.out.println("Name: " + deck.getName());
                System.out.println("Description: " + deck.getDescription());
                System.out.println("Cards count: " + cardCount);
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.out.println("Error while viewing decks. Please try again.");
        }
    }
}