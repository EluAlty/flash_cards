package presentation.commands;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.observers.StudyObserver;
import application.ports.in.DeckManagementInputPort;
import application.strategies.StudyStrategy;
import domain.entities.Card;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudyDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;
    private final StudyStrategy studyStrategy;
    private final StudyObserver studyObserver;

    public StudyDeckCommand(Scanner scanner, DeckManagementInputPort deckService, StudyStrategy studyStrategy, StudyObserver studyObserver) {
        this.scanner = scanner;
        this.deckService = deckService;
        this.studyStrategy = studyStrategy;
        this.studyObserver = studyObserver;
    }

    @Override
    public void execute() {
        System.out.print("Enter deck ID to study: ");
        String deckId = scanner.nextLine();

        DeckDto deckDto = deckService.getDeck(deckId);
        if (deckDto == null && deckDto.getCards() == null && deckDto.getCards().isEmpty()) {
            System.out.println("Deck not found or empty.");
            return;
        }

        List<Card> orderedCards = studyStrategy.orderCardsForStudy(deckDto.getCards().stream()
                .map(cardDto -> new Card(cardDto.getId(), cardDto.getQuestion(), cardDto.getAnswer()) {
                    @Override
                    public String getType() {
                        return cardDto.getType();
                    }
                })
                .collect(Collectors.toList()));

        for (Card card : orderedCards) {
            System.out.println("\nCard ID: " + card.getId());
            System.out.println("Question: " + card.getQuestion());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            boolean correct = card.getAnswer().equalsIgnoreCase(userAnswer.trim());
            System.out.println(correct ? "Correct!" : "Incorrect. The correct answer is: " + card.getAnswer());
            studyObserver.onCardStudied(card, correct);
        }
    }
}