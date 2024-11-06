package presentation.commands;

import application.services.DeckService;
import java.util.Scanner;

public class RemoveCardCommand implements Command {
    private final Scanner scanner;
    private final DeckService deckService;

    public RemoveCardCommand(Scanner scanner, DeckService deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name: ");
        String deckName = scanner.nextLine();
        
        System.out.print("Enter card ID: ");
        String cardId = scanner.nextLine();
        
        deckService.removeCard(deckName, cardId);
        System.out.println("Card removed successfully!");
    }
} 