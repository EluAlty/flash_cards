package application.factories;

import domain.entities.Card;

public interface CardFactory {
    Card createCard(String id, String question, String answer, int difficulty);
}