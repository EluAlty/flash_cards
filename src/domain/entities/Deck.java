package domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deck implements Serializable {
    private String id;
    private String name;
    private String description;
    private List<Card> cards;

    public Deck(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(String cardId) {
        cards.removeIf(card -> card.getId().equals(cardId));
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Card> getCards() { return cards; }
    public void setCards(List<Card> cards) { this.cards = cards; }
}