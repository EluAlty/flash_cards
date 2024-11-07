package application.factories;

import domain.entities.Card;
import domain.entities.TextCard;

public class TextCardFactory implements CardFactory {
    @Override
    public Card createCard(String id, String question, String answer, int difficulty) {
        return new TextCard(id, question, answer, difficulty);
    }
}