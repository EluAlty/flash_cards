package presentation.commands;

import application.services.DeckService;
import java.util.Scanner;

public class DeleteDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckService deckService;

    public DeleteDeckCommand(Scanner scanner, DeckService deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name to delete: ");
        String deckName = scanner.nextLine();
        
        deckService.deleteDeck(deckName);
        System.out.println("Deck deleted successfully!");
    }
} 