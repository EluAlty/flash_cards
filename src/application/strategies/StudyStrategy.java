package application.strategies;

import domain.entities.Card;
import java.util.List;

public interface StudyStrategy {
    List<Card> orderCardsForStudy(List<Card> cards);
}