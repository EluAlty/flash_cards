package presentation.ui;

import application.observers.StudyObserver;
import application.ports.in.DeckManagementInputPort;
import application.strategies.StudyStrategy;
import application.usecases.CreateDeckUseCase;
import presentation.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private static final String BACK_COMMAND = "back";
    private final Scanner scanner;
    private final Map<Integer, Command> commands;

    public ConsoleUI(DeckManagementInputPort deckService,
                    StudyStrategy randomStrategy,
                    StudyStrategy difficultyStrategy,
                    StudyObserver studyObserver,
                    CreateDeckUseCase createDeckUseCase) {
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();
        initializeCommands(deckService, randomStrategy, difficultyStrategy, studyObserver, createDeckUseCase);
    }

    private void initializeCommands(DeckManagementInputPort deckService,
                                    StudyStrategy randomStrategy,
                                    StudyStrategy difficultyStrategy,
                                    StudyObserver studyObserver,
                                    CreateDeckUseCase createDeckUseCase) {
        commands.put(1, new CreateDeckCommand(scanner, createDeckUseCase));
        commands.put(2, new ViewDecksCommand(deckService));
        commands.put(3, new AddCardCommand(scanner, deckService));
        commands.put(4, new RemoveCardCommand(scanner, deckService));
        commands.put(5, new DeleteDeckCommand(scanner, deckService));
        commands.put(6, new StudyDeckCommand(scanner, deckService, randomStrategy, difficultyStrategy, studyObserver));
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase(BACK_COMMAND)) {
                    continue;
                } else if (choice.equalsIgnoreCase("0")) {
                    running = false;
                } else {
                    Command command = commands.get(Integer.parseInt(choice));
                    if (command != null) {
                        command.execute();
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    private void printMenu() {
        System.out.println("\n=== Flashcards Application ===");
        System.out.println("1. Create new deck");
        System.out.println("2. View all decks");
        System.out.println("3. Add card to deck");
        System.out.println("4. Remove card from deck");
        System.out.println("5. Delete deck");
        System.out.println("6. Study deck");
        System.out.println("0. Exit");
        System.out.print("Choose action: ");
    }

    private String readInputWithBack(String prompt) {
        System.out.print(prompt + " (or enter 'back' to return): ");
        String input = scanner.nextLine().trim();
        return input;
    }
}