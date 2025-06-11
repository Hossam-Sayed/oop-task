package model;

import java.util.Arrays;
import java.util.List;

public class TrueFalseQuestion extends Question {

    // Internal representation of valid True/False answers
    private static final List<String> TRUE_FALSE_OPTIONS = Arrays.asList("True", "False");

    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText, TRUE_FALSE_OPTIONS, correctAnswer);
        // Validate that the correct answer is indeed "True" or "False"
        if (!correctAnswer.equalsIgnoreCase("True") && !correctAnswer.equalsIgnoreCase("False")) {
            throw new IllegalArgumentException("Correct answer for True/False Question must be 'True' or 'False'.");
        }
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return userAnswer.trim().equalsIgnoreCase(correctAnswer);
    }

    @Override
    public String getFormattedQuestion() {
        return getQuestionText() + "\n1. True\n2. False\n";
    }
}