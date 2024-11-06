package presentation;

import application.services.DeckService;
import application.strategies.StudyStrategy;
import application.observers.StudyObserver;
import presentation.commands.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final Map<Integer, Command> commands;

    public ConsoleUI(DeckService deckService, StudyStrategy studyStrategy, StudyObserver studyObserver) {
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();
        initializeCommands(deckService, studyStrategy, studyObserver);
    }

    private void initializeCommands(DeckService deckService, StudyStrategy studyStrategy, StudyObserver studyObserver) {
        commands.put(1, new CreateDeckCommand(scanner, deckService));
        commands.put(2, new ViewDecksCommand(deckService));
        commands.put(3, new AddCardCommand(scanner, deckService));
        commands.put(4, new RemoveCardCommand(scanner, deckService));
        commands.put(5, new DeleteDeckCommand(scanner, deckService));
        commands.put(6, new StudyDeckCommand(scanner, deckService, studyStrategy, studyObserver));
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                running = false;
            } else {
                Command command = commands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
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
} 