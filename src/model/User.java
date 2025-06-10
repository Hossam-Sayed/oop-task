package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private List<String> userAnswers; // To record answers
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
        return new ArrayList<>(userAnswers); // Return a copy to prevent external modification
    }

    public void recordAnswer(String answer) {
        this.userAnswers.add(answer);
    }

    public void incrementScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void resetProgress() {
        this.userAnswers.clear();
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
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