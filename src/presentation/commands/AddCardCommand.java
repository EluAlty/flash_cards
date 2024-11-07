package presentation.commands;

import application.dto.CardDto;
import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;
import java.util.UUID;

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
        System.out.print("Enter card type (TEXT/IMAGE): ");
        String type = scanner.nextLine().toUpperCase();

        CardDto cardDto = new CardDto(UUID.randomUUID().toString(), question, answer, type);
        deckService.addCard(deckId, cardDto);
        System.out.println("Card added successfully.");
    }
}