package model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Question {
    private final String questionText;
    protected final List<String> possibleAnswers;
    protected final String correctAnswer;

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

    /**
     * Checks if the user's answer is correct for this specific question type.
     * This method will be implemented by concrete subclasses.
     *
     * @param userAnswer The user's input string.
     * @return true if the answer is correct, false otherwise.
     */
    public abstract boolean isCorrect(String userAnswer);

    /**
     * Returns a formatted string representation of the question for display to the user.
     * This method will be implemented by concrete subclasses to provide type-specific
     * display logic.
     *
     * @return A string representing the question and its options.
     */
    public abstract String getFormattedQuestion();


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