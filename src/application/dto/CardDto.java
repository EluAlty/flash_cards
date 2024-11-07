package application.dto;

public class CardDto {
    private final String id;
    private final String question;
    private final String answer;
    private final String type;
    private final int difficulty;

    public CardDto(String id, String question, String answer, String type, int difficulty) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public int getDifficulty() {
        return difficulty;
    }
}