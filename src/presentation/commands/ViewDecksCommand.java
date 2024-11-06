// src/presentation/commands/ViewDecksCommand.java
package presentation.commands;

import application.dto.DeckDto;
import application.dto.CardDto;
import application.ports.in.DeckManagementInputPort;
import java.util.List;

public class ViewDecksCommand implements Command {
    private final DeckManagementInputPort deckService;

    public ViewDecksCommand(DeckManagementInputPort deckService) {
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        List<DeckDto> decks = deckService.getAllDecks();
        if (decks.isEmpty()) {
            System.out.println("No decks available.");
            return;
        }

        System.out.println("\nAvailable decks:");
        for (DeckDto deck : decks) {
            System.out.println("\nDeck: " + deck.getName() + " (ID: " + deck.getId() + ")");
            System.out.println("Description: " + deck.getDescription());
            List<CardDto> cards = deck.getCards();
            if (cards.isEmpty()) {
                System.out.println("  No cards in this deck");
            } else {
                System.out.println("  Cards:");
                for (CardDto card : cards) {
                    System.out.println("  - ID: " + card.getId());
                    System.out.println("    Question: " + card.getQuestion());
                    System.out.println("    Difficulty: " + card.getDifficulty());
                }
            }
        }
    }
}