package presentation.commands;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;

public class RemoveCardCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;
    private static final String BACK_COMMAND = "back";

    public RemoveCardCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Enter deck name (or 'back' to return): ");
            String deckName = scanner.nextLine().trim();
            
            if (deckName.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            DeckDto deck = findDeckByName(deckName);
            if (deck == null || deck.getCards() == null || deck.getCards().isEmpty()) {
                System.out.println("Deck not found or empty.");
                return;
            }

            System.out.println("Cards in the deck:");
            for (CardDto card : deck.getCards()) {
                System.out.println("Question: " + card.getQuestion());
            }

            System.out.print("Enter question of card to remove (or 'back' to return): ");
            String question = scanner.nextLine().trim();
            
            if (question.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            CardDto cardToRemove = deck.getCards().stream()
                .filter(card -> card.getQuestion().equalsIgnoreCase(question))
                .findFirst()
                .orElse(null);

            if (cardToRemove == null) {
                System.out.println("Card not found.");
                return;
            }

            deckService.removeCard(deck.getId(), cardToRemove.getId());
            System.out.println("Card removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing card. Please try again.");
        }
    }

    private DeckDto findDeckByName(String name) {
        try {
            return deckService.getAllDecks().stream()
                .filter(deck -> deck.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(deck -> deckService.getDeck(deck.getId()))
                .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}