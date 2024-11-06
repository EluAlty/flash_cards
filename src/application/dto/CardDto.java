package application.dto;

public class CardDto {
    private String id;
    private String question;
    private String answer;
    private int difficulty;

    public CardDto(String id, String question, String answer, int difficulty) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}
