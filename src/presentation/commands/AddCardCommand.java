package presentation.commands;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import java.util.Scanner;
import java.util.UUID;

public class AddCardCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;
    private static final String BACK_COMMAND = "back";

    public AddCardCommand(Scanner scanner, DeckManagementInputPort deckService) {
        this.scanner = scanner;
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Enter deck name (or 'back' to return): ");
            String deckName = scanner.nextLine().trim();
            
            if (deckName.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            DeckDto deck = findDeckByName(deckName);
            if (deck == null) {
                System.out.println("Deck not found.");
                return;
            }

            System.out.print("Enter question (or 'back' to return): ");
            String question = scanner.nextLine().trim();
            
            if (question.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            System.out.print("Enter answer (or 'back' to return): ");
            String answer = scanner.nextLine().trim();
            
            if (answer.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            System.out.print("Enter difficulty (1-Easy, 2-Medium, 3-Hard): ");
            String difficultyInput = scanner.nextLine().trim();
            
            if (difficultyInput.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            int difficulty;
            try {
                difficulty = Integer.parseInt(difficultyInput);
                if (difficulty < 1 || difficulty > 3) {
                    System.out.println("Invalid difficulty. Please enter 1, 2, or 3.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }

            CardDto cardDto = new CardDto(UUID.randomUUID().toString(), question, answer, "TEXT", difficulty);
            deckService.addCard(deck.getId(), cardDto);
            System.out.println("Card successfully added.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private DeckDto findDeckByName(String name) {
        try {
            return deckService.getAllDecks().stream()
                .filter(deck -> deck.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}