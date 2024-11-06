package application.dto;

import java.util.List;

public class DeckDto {
    private String id;
    private String name;
    private String description;
    private List<CardDto> cards;

    public DeckDto(String id, String name, String description, List<CardDto> cards) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cards = cards;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<CardDto> getCards() { return cards; }
    public void setCards(List<CardDto> cards) { this.cards = cards; }
}