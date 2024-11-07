package domain.entities;

import java.io.Serializable;

public abstract class Card implements Serializable {
    protected String id;
    protected String question;
    protected String answer;
    protected int difficulty;

    public Card(String id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.difficulty = 1;
    }

    public abstract String getType();

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { if (difficulty >= 1 && difficulty <= 5) {
        this.difficulty = difficulty;
    }
    }
}