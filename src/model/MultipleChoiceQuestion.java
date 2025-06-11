package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(String questionText, List<String> possibleAnswers, String correctAnswer) {
        super(questionText, possibleAnswers, correctAnswer);
        // Ensure that the provided correct answer is one of the possible answers.
        if (!possibleAnswers.contains(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer must be one of the possible answers for MultipleChoiceQuestion.");
        }
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return userAnswer.trim().equalsIgnoreCase(correctAnswer);
    }

    @Override
    public String getFormattedQuestion() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionText()).append("\n");
        // Display options with numbers
        for (int i = 0; i < possibleAnswers.size(); i++) {
            sb.append(String.format("%d. %s\n", (i + 1), possibleAnswers.get(i)));
        }
        return sb.toString();
    }
}