package domain.entities;

public class ImageCard extends Card {
    private String imageUrl;

    public ImageCard(String id, String question, String answer, String imageUrl) {
        super(id, question, answer);
        this.imageUrl = imageUrl;
    }

    @Override
    public String getType() {
        return "IMAGE";
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}