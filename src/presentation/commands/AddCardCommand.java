package presentation.commands;

import application.services.DeckService;
import java.util.Scanner;

public class AddCardCommand implements Command {
    private final Scanner scanner;
    private final DeckService deckService;

    public AddCardCommand(Scanner scanner, DeckService deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name: ");
        String deckName = scanner.nextLine();
        
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        
        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();
        
        System.out.print("Enter difficulty (1-5): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine();
        
        deckService.addCard(deckName, question, answer, difficulty);
        System.out.println("Card added successfully!");
    }
} 