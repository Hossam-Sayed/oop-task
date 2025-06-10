package model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String questionText, List<String> possibleAnswers, String correctAnswer) {
        super(questionText, possibleAnswers, correctAnswer);
    }
}
