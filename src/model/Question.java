package model;

import java.util.List;

import java.util.Collections;
import java.util.Objects;

public class Question {
    private final String questionText;
    private final List<String> possibleAnswers;
    private final String correctAnswer;

    public Question(String questionText, List<String> possibleAnswers, String correctAnswer) {
        if (questionText == null || questionText.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be null or empty.");
        }
        if (possibleAnswers == null || possibleAnswers.isEmpty()) {
            throw new IllegalArgumentException("Possible answers cannot be null or empty.");
        }
        if (!possibleAnswers.contains(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer must be one of the possible answers.");
        }

        this.questionText = questionText;
        this.possibleAnswers = Collections.unmodifiableList(possibleAnswers);
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public boolean isCorrect(String userAnswer) {
        // Case-insensitive comparison for user answers
        return correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionText).append("\n");
        for (int i = 0; i < possibleAnswers.size(); i++) {
            sb.append(String.format("%d. %s\n", (i + 1), possibleAnswers.get(i)));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText) &&
                Objects.equals(possibleAnswers, question.possibleAnswers) &&
                Objects.equals(correctAnswer, question.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, possibleAnswers, correctAnswer);
    }
}