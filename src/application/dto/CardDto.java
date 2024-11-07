package application.dto;

public class CardDto {
    private String id;
    private String question;
    private String answer;
    private String type;

    public CardDto(String id, String question, String answer, String type) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}