package domain.entities;

public class ImageCard extends Card {
    public ImageCard(String id, String question, String answer, int difficulty) {
        super(id, question, answer, difficulty);
    }

    @Override
    public String getType() {
        return "IMAGE";
    }
}