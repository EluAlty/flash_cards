package presentation.commands;

import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;

public class DeleteDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;

    public DeleteDeckCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck ID to delete: ");
        String deckId = scanner.nextLine();

        deckService.deleteDeck(deckId);
        System.out.println("Deck deleted successfully.");
    }
}