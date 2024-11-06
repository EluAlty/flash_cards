package domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Deck {
    private String id;
    private String name;
    private String description;
    private List<Card> cards;

    private Deck(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(String cardId) {
        cards.removeIf(card -> card.getId().equals(cardId));
    }

    // Builder Pattern
    public static class Builder {
        private String id;
        private String name;
        private String description;

        public Builder() {
            this.id = UUID.randomUUID().toString();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Deck build() {
            return new Deck(this);
        }
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Card> getCards() { return new ArrayList<>(cards); }
} 