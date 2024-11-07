package domain.entities;

import java.io.Serializable;

public abstract class Card implements Serializable {
    private final String id;
    private final String question;
    private final String answer;
    private final int difficulty;

    public Card(String id, String question, String answer, int difficulty) {
        this.id = id;
        this.question = question;
        this.answer = answer;
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

    public int getDifficulty() {
        return difficulty;
    }

    public abstract String getType();
}