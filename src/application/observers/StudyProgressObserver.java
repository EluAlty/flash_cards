package application.observers;

import domain.entities.Card;

public class StudyProgressObserver implements StudyObserver {
    private int totalCards = 0;
    private int correctAnswers = 0;

    @Override
    public void onCardStudied(Card card, boolean correct) {
        totalCards++;
        if (correct) {
            correctAnswers++;
        }
        System.out.println("Study progress: " + correctAnswers + "/" + totalCards + " correct");
    }
}