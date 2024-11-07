package domain.entities;

public abstract class CardDecorator extends Card {
    protected Card decoratedCard;

    public CardDecorator(Card decoratedCard) {
        super(decoratedCard.getId(), decoratedCard.getQuestion(), decoratedCard.getAnswer());
        this.decoratedCard = decoratedCard;
    }

    @Override
    public String getType() {
        return decoratedCard.getType();
    }
}