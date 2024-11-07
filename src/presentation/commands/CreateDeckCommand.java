package presentation.commands;

import application.usecases.CreateDeckUseCase;
import java.util.Scanner;

public class CreateDeckCommand implements Command {
    private final Scanner scanner;
    private final CreateDeckUseCase createDeckUseCase;
    private static final String BACK_COMMAND = "back";

    public CreateDeckCommand(Scanner scanner, CreateDeckUseCase createDeckUseCase) {
        this.scanner = scanner;
        this.createDeckUseCase = createDeckUseCase;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name (or 'back' to return): ");
        String name = scanner.nextLine().trim();
        
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            return;
        }

        System.out.print("Enter deck description (or 'back' to return): ");
        String description = scanner.nextLine().trim();
        
        if (description.equalsIgnoreCase(BACK_COMMAND)) {
            return;
        }

        createDeckUseCase.execute(name, description);
        System.out.println("Deck created successfully.");
    }
}