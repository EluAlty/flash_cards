package presentation.commands;

import application.services.DeckService;
import domain.entities.Deck;
import domain.entities.Card;
import java.util.List;

public class ViewDecksCommand implements Command {
    private final DeckService deckService;

    public ViewDecksCommand(DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        List<Deck> decks = deckService.getAllDecks();
        if (decks.isEmpty()) {
            System.out.println("No decks available.");
            return;
        }
        
        System.out.println("\nAvailable decks:");
        for (Deck deck : decks) {
            System.out.println("\nDeck: " + deck.getName());
            List<Card> cards = deck.getCards();
            if (cards.isEmpty()) {
                System.out.println("  No cards in this deck");
            } else {
                System.out.println("  Cards:");
                for (Card card : cards) {
                    System.out.println("  - ID: " + card.getId());
                    System.out.println("    Question: " + card.getQuestion());
                    System.out.println("    Difficulty: " + card.getDifficulty());
                }
            }
        }
    }
} 