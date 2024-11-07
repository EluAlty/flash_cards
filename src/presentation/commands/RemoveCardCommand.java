package presentation.commands;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;

public class RemoveCardCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;

    public RemoveCardCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck ID: ");
        String deckId = scanner.nextLine();

        DeckDto deck = deckService.getDeck(deckId);
        if (deck == null && deck.getCards() == null && deck.getCards().isEmpty()) {
            System.out.println("Deck not found or empty.");
            return;
        }

        System.out.println("Cards in the deck:");
        for (CardDto card : deck.getCards()) {
            System.out.println("ID: " + card.getId() + ", Question: " + card.getQuestion());
        }

        System.out.print("Enter card ID to remove: ");
        String cardId = scanner.nextLine();

        deckService.removeCard(deckId, cardId);
        System.out.println("Card removed successfully.");
    }
}