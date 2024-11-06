// src/application/strategies/DifficultyBasedStrategy.java
package application.strategies;

import domain.entities.Card;
import domain.entities.Deck;
import application.observers.StudyObserver;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DifficultyBasedStrategy implements StudyStrategy {
    @Override
    public List<Card> prepareCards(List<Card> cards) {
        return cards.stream()
                .sorted(Comparator.comparingInt(Card::getDifficulty).reversed())
                .toList();
    }

    @Override
    public void study(Deck deck, StudyObserver observer) {
        List<Card> cards = prepareCards(deck.getCards());
        Scanner scanner = new Scanner(System.in);

        for (Card card : cards) {
            System.out.println("\nQuestion: " + card.getQuestion());
            System.out.print("Press Enter to see the answer...");
            scanner.nextLine();

            System.out.println("Answer: " + card.getAnswer());
            observer.onCardStudied(card);
        }

        scanner.close();
        observer.onStudySessionCompleted(cards.size());
    }
}