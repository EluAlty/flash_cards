// src/presentation/commands/AddCardCommand.java
package presentation.commands;

import application.dto.CardDto;
import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;

public class AddCardCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;

    public AddCardCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck ID: ");
        String deckId = scanner.nextLine();

        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        System.out.print("Enter difficulty (1-5): ");
        int difficulty = Integer.parseInt(scanner.nextLine());

        CardDto cardDto = new CardDto(null, question, answer, difficulty);
        deckService.addCardToDeck(deckId, cardDto);
        System.out.println("Card added successfully!");
    }
}