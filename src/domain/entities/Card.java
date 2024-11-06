package domain.entities;

public class Card {
    private String id;
    private String question;
    private String answer;
    private int difficulty;

    private Card(Builder builder) {
        this.id = builder.id;
        this.question = builder.question;
        this.answer = builder.answer;
        this.difficulty = builder.difficulty;
    }

   
    public static class Builder {
        private String id;
        private String question;
        private String answer;
        private int difficulty;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder question(String question) {
            this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder difficulty(int difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }

   
    public String getId() { return id; }
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public int getDifficulty() { return difficulty; }
} 