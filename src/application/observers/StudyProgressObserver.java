package application.observers;

import domain.entities.Card;

public class StudyProgressObserver implements StudyObserver {
    private int cardsStudied = 0;

    @Override
    public void onCardStudied(Card card) {
        cardsStudied++;
        System.out.println("Progress: " + cardsStudied + " cards studied");
    }

    @Override
    public void onStudySessionCompleted(int totalCards) {
        System.out.println("Session completed! Studied " + cardsStudied + "/" + totalCards + " cards");
        cardsStudied = 0;
    }
} 