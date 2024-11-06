// src/presentation/commands/RemoveCardCommand.java
package presentation.commands;

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

        System.out.print("Enter card ID: ");
        String cardId = scanner.nextLine();

        deckService.removeCardFromDeck(deckId, cardId);
        System.out.println("Card removed successfully!");
    }
}
