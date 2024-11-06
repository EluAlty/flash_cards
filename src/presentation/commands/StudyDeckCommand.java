// src/presentation/commands/StudyDeckCommand.java
package presentation.commands;

import application.dto.DeckDto;
import application.observers.StudyObserver;
import application.ports.in.DeckManagementInputPort;
import application.strategies.StudyStrategy;
import domain.entities.Deck;
import domain.entities.Card;

import java.util.Scanner;
import java.util.stream.Collectors;

public class StudyDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;
    private final StudyStrategy studyStrategy;
    private final StudyObserver studyObserver;

    public StudyDeckCommand(Scanner scanner, DeckManagementInputPort deckService,
                            StudyStrategy studyStrategy, StudyObserver studyObserver) {
        this.scanner = scanner;
        this.deckService = deckService;
        this.studyStrategy = studyStrategy;
        this.studyObserver = studyObserver;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck ID to study: ");
        String deckId = scanner.nextLine();

        DeckDto deckDto = deckService.getDeckById(deckId);
        if (deckDto == null) {
            System.out.println("Deck not found.");
            return;
        }

        Deck deck = new Deck.Builder()
                .name(deckDto.getName())
                .description(deckDto.getDescription())
                .build();

        deckDto.getCards().forEach(cardDto ->
                deck.addCard(new Card.Builder()
                        .id(cardDto.getId())
                        .question(cardDto.getQuestion())
                        .answer(cardDto.getAnswer())
                        .difficulty(cardDto.getDifficulty())
                        .build()));

        studyStrategy.study(deck, studyObserver);
    }
}