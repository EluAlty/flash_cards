package application.factories;

import domain.entities.Card;
import domain.entities.ImageCard;

public class ImageCardFactory implements CardFactory {
    @Override
    public Card createCard(String id, String question, String answer, int difficulty) {
        return new ImageCard(id, question, answer, difficulty);
    }
}