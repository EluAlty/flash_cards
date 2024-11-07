package domain.entities;

public class TextCard extends Card {
    public TextCard(String id, String question, String answer) {
        super(id, question, answer);
    }

    @Override
    public String getType() {
        return "TEXT";
    }
}