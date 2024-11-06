package application.strategies;

import domain.entities.Card;
import domain.entities.Deck;
import application.observers.StudyObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RandomStudyStrategy implements StudyStrategy {
    @Override
    public List<Card> prepareCards(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }

    @Override
    public void study(Deck deck, StudyObserver observer) {
        List<Card> cards = prepareCards(deck.getCards());
        Scanner scanner = new Scanner(System.in);
        
        for (Card card : cards) {
            System.out.println("\nQuestion: " + card.getQuestion());
            System.out.print("Press Enter to see answer...");
            scanner.nextLine();
            
            System.out.println("Answer: " + card.getAnswer());
            observer.onCardStudied(card);
            
            System.out.print("Press Enter for next card...");
            scanner.nextLine();
        }
        
        scanner.close();
        observer.onStudySessionCompleted(cards.size());
    }
} 