package presentation.commands;

import application.usecases.CreateDeckUseCase;
import java.util.Scanner;

public class CreateDeckCommand implements Command {
    private final Scanner scanner;
    private final CreateDeckUseCase createDeckUseCase;

    public CreateDeckCommand(Scanner scanner, CreateDeckUseCase createDeckUseCase) {
        this.scanner = scanner;
        this.createDeckUseCase = createDeckUseCase;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name: ");
        String name = scanner.nextLine();
        System.out.print("Enter deck description: ");
        String description = scanner.nextLine();
        createDeckUseCase.execute(name, description);
        System.out.println("Deck created successfully.");
    }
}