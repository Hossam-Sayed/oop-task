package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final List<String> userAnswers;
    private int score;

    public User(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
        this.name = name;
        this.userAnswers = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public List<String> getUserAnswers() {
        return new ArrayList<>(userAnswers);
    }

    public void recordAnswer(String answer) {
        this.userAnswers.add(answer);
    }

    public void incrementScore() {
        this.score++;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative.");
        }
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void resetProgress() {
        this.userAnswers.clear();
        this.score = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}