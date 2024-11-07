package application.strategies;

import domain.entities.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStudyStrategy implements StudyStrategy {
    @Override
    public List<Card> orderCardsForStudy(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }
}