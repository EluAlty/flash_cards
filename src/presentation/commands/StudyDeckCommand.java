package presentation.commands;

import application.services.DeckService;
import application.strategies.StudyStrategy;
import application.observers.StudyObserver;
import java.util.Scanner;

public class StudyDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckService deckService;
    private final StudyStrategy studyStrategy;
    private final StudyObserver studyObserver;

    public StudyDeckCommand(Scanner scanner, DeckService deckService, 
                          StudyStrategy studyStrategy, StudyObserver studyObserver) {
        this.scanner = scanner;
        this.deckService = deckService;
        this.studyStrategy = studyStrategy;
        this.studyObserver = studyObserver;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck name to study: ");
        String deckName = scanner.nextLine();
        
        deckService.getDeckById(deckName).ifPresent(deck -> 
            studyStrategy.study(deck, studyObserver));
    }
} 