package domain.entities;

public class TimedCard extends CardDecorator {
    private int timeLimit;

    public TimedCard(Card decoratedCard, int timeLimit) {
        super(decoratedCard);
        this.timeLimit = timeLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}