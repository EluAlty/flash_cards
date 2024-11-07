package domain.entities;

public abstract class CardDecorator extends Card {
    protected Card decoratedCard;

    public CardDecorator(Card card) {
        super(card.getId(), card.getQuestion(), card.getAnswer(), card.getDifficulty());
        this.decoratedCard = card;
    }

    @Override
    public String getType() {
        return decoratedCard.getType();
    }
}