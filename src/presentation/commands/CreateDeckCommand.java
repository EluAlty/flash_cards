package presentation.commands;

import application.services.DeckService;
import java.util.Scanner;

public class CreateDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckService deckService;

    public CreateDeckCommand(Scanner scanner, DeckService deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name: ");
        String name = scanner.nextLine();
        deckService.createDeck(name);
        System.out.println("Deck created successfully!");
    }
} 