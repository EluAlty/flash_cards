package presentation.commands;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.observers.StudyObserver;
import application.ports.in.DeckManagementInputPort;
import application.strategies.StudyStrategy;
import domain.entities.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudyDeckCommand implements Command {
    private final Scanner scanner;
    private final DeckManagementInputPort deckService;
    private final Map<Integer, StudyStrategy> strategies;
    private final StudyObserver studyObserver;
    private static final String BACK_COMMAND = "back";

    public StudyDeckCommand(Scanner scanner, DeckManagementInputPort deckService, 
                          StudyStrategy randomStrategy, 
                          StudyStrategy difficultyStrategy, 
                          StudyObserver studyObserver) {
        this.scanner = scanner;
        this.deckService = deckService;
        this.studyObserver = studyObserver;
        this.strategies = new HashMap<>();
        strategies.put(1, randomStrategy);
        strategies.put(2, difficultyStrategy);
    }

    @Override
    public void execute() {
        try {
            System.out.print("Enter deck name (or 'back' to return): ");
            String deckName = scanner.nextLine().trim();
            
            if (deckName.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            DeckDto deckDto = findDeckByName(deckName);
            if (deckDto == null || deckDto.getCards() == null || deckDto.getCards().isEmpty()) {
                System.out.println("Deck not found or empty.");
                return;
            }

            System.out.println("Choose study strategy (or 'back' to return):");
            System.out.println("1. Random");
            System.out.println("2. By difficulty");
            
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase(BACK_COMMAND)) {
                return;
            }

            int strategyChoice;
            try {
                strategyChoice = Integer.parseInt(choice);
                if (!strategies.containsKey(strategyChoice)) {
                    System.out.println("Invalid strategy choice. Please try again.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }

            StudyStrategy selectedStrategy = strategies.get(strategyChoice);
            studyCards(deckDto, selectedStrategy);
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private DeckDto findDeckByName(String name) {
        try {
            return deckService.getAllDecks().stream()
                .filter(deck -> deck.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(deck -> deckService.getDeck(deck.getId()))
                .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    private void studyCards(DeckDto deckDto, StudyStrategy selectedStrategy) {
        try {
            List<Card> orderedCards = selectedStrategy.orderCardsForStudy(
                deckDto.getCards().stream()
                    .map(cardDto -> new Card(cardDto.getId(), cardDto.getQuestion(), 
                            cardDto.getAnswer(), cardDto.getDifficulty()) {
                        @Override
                        public String getType() {
                            return cardDto.getType();
                        }
                    })
                    .collect(Collectors.toList())
            );

            for (Card card : orderedCards) {
                System.out.println("\nQuestion: " + card.getQuestion());
                System.out.print("Your answer (or 'back' to return): ");
                String userAnswer = scanner.nextLine().trim();
                
                if (userAnswer.equalsIgnoreCase(BACK_COMMAND)) {
                    return;
                }

                boolean correct = card.getAnswer().equalsIgnoreCase(userAnswer);
                System.out.println(correct ? "Correct!" : "Incorrect. The correct answer is: " + card.getAnswer());
                studyObserver.onCardStudied(card, correct);
            }
        } catch (Exception e) {
            System.out.println("An error occurred during study. Please try again.");
        }
    }
}