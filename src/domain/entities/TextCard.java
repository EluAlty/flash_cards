package domain.entities;

public class TextCard extends Card {
    public TextCard(String id, String question, String answer, int difficulty) {
        super(id, question, answer, difficulty);
    }

    @Override
    public String getType() {
        return "TEXT";
    }
}