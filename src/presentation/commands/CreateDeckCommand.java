// src/presentation/commands/CreateDeckCommand.java
package presentation.commands;

import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;

public class CreateDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;

    public CreateDeckCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name: ");
        String name = scanner.nextLine();
        System.out.print("Enter deck description: ");
        String description = scanner.nextLine();
        deckService.createDeck(name, description);
        System.out.println("Deck created successfully!");
    }
}
