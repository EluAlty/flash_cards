package application.strategies;

import domain.entities.Card;
import domain.entities.Deck;
import application.observers.StudyObserver;
import java.util.List;

public interface StudyStrategy {
    List<Card> prepareCards(List<Card> cards);
    void study(Deck deck, StudyObserver observer);
} 