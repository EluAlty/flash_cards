package application.observers;

import domain.entities.Card;

public interface StudyObserver {
    void onCardStudied(Card card, boolean correct);
}