package application.factories;

import domain.entities.Card;
import domain.entities.ImageCard;

public class ImageCardFactory implements CardFactory {
    @Override
    public Card createCard(String id, String question, String answer) {
        String[] parts = answer.split("\\|");
        String textAnswer = parts[0];
        String imageUrl = parts.length > 1 ? parts[1] : "";
        return new ImageCard(id, question, textAnswer, imageUrl);
    }
}