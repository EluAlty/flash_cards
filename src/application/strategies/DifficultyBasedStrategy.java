package application.strategies;

import domain.entities.Card;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DifficultyBasedStrategy implements StudyStrategy {
    @Override
    public List<Card> orderCardsForStudy(List<Card> cards) {
        return cards.stream()
                .sorted(Comparator.comparingInt(Card::getDifficulty).reversed())
                .collect(Collectors.toList());
    }
}